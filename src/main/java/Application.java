import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoGameMoney;
import domain.Lottos;
import domain.RandomLottoNumbersGenerator;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoGameMoney purchaseMoney = new LottoGameMoney(InputView.getPurchaseAmount());
        final int manualLottoCount = InputView.getManualLottoCount();
        final LottoGame lottoGame = createLottoGame(purchaseMoney);
        OutputView.showPurchasedLottos(lottoGame.getLottos());

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate());
    }

    private static LottoGame createLottoGame(LottoGameMoney purchaseMoney) {
        int lottoCount = purchaseMoney.purchasableLottoCount();
        Lottos lottos = createLottos(lottoCount);

        return new LottoGame(purchaseMoney, lottos);
    }

    private static Lottos createLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = LottoFactory.createLotto(new RandomLottoNumbersGenerator());
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    private static WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningLottoNumbers();
        Lotto winningLotto = LottoFactory.createLotto(winningNumbers);

        final int bonusNumber = InputView.getBonusBall();
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);

        return new WinningLotto(winningLotto, bonusLottoNumber);
    }
}
