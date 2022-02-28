import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoMoney;
import domain.LottoPurchaseCount;
import domain.Lottos;
import domain.strategy.RandomLottoGeneratorStrategy;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoMoney lottoMoney = new LottoMoney(InputView.getPurchaseMoney());
        final LottoGame lottoGame = new LottoGame(lottoMoney.calculateLottoCount(), new RandomLottoGeneratorStrategy());
        OutputView.showPurchasedLottos(lottoGame.getLottos());

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate());
    }

    private static WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningLottoNumbers();
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
        Lotto winningLotto = new Lotto(winningLottoNumbers);

        final int bonusNumber = InputView.getBonusBall();
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);

        return new WinningLotto(winningLotto, bonusLottoNumber);
    }

    private static LottoPurchaseCount createLottoPurchaseCount(LottoMoney lottoMoney) {
        try {
            return lottoMoney.calculateLottoCountRefactor(InputView.getPurchaseManualCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottoPurchaseCount(lottoMoney);
        }
    }

    private static Lottos createLottos(LottoPurchaseCount lottoPurchaseCount) {
        try {
            return LottoFactory.generateLottos(
                InputView.getManualLottoNumbers(lottoPurchaseCount.getManualCount()),
                lottoPurchaseCount,
                new RandomLottoGeneratorStrategy());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottos(lottoPurchaseCount);
        }
    }
}
