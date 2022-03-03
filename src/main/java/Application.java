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
        final LottoGame lottoGame = createLottoGame(purchaseMoney);

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate());
    }

    private static LottoGame createLottoGame(LottoGameMoney purchaseMoney) {
        Lottos lottos = createLottos(purchaseMoney);

        return new LottoGame(purchaseMoney, lottos);
    }

    private static Lottos createLottos(LottoGameMoney purchaseMoney) {
        final int manualLottoCount = InputView.getManualLottoCount();
        purchaseMoney.checkPurchasableLottoCount(manualLottoCount);
        final List<Lotto> lottos = new ArrayList<>(createManualLottos(manualLottoCount));

        final int autoLottoCount = purchaseMoney.purchasableLottoCount() - manualLottoCount;
        lottos.addAll(createAutoLottos(autoLottoCount));

        OutputView.showPurchasedLottos(manualLottoCount, autoLottoCount, lottos);

        return new Lottos(lottos);
    }

    private static List<Lotto> createManualLottos(int lottoCount) {
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(lottoCount);
        return LottoFactory.createLottos(manualLottoNumbers);
    }

    private static List<Lotto> createAutoLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = LottoFactory.createLotto(new RandomLottoNumbersGenerator());
            lottos.add(lotto);
        }

        return lottos;
    }

    private static WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningLottoNumbers();
        Lotto winningLotto = LottoFactory.createLotto(winningNumbers);

        final int bonusNumber = InputView.getBonusBall();
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);

        return new WinningLotto(winningLotto, bonusLottoNumber);
    }
}
