import java.util.List;

import domain.Lotto;
import domain.LottoCount;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.LottoGameMoney;
import domain.Lottos;
import domain.RandomLottoNumbersGenerator;
import domain.WinningLotto;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoGameMoney purchaseMoney = new LottoGameMoney(InputView.getPurchaseAmount());
        Lottos lottos = createLottos(purchaseMoney);
        final WinningLotto winningLotto = createWinningLotto();

        final WinningResult winningResult = lottos.match(purchaseMoney, winningLotto);
        OutputView.showWinningResult(winningResult.values());
        OutputView.showProfitRate(winningResult.profitRate());
    }

    private static Lottos createLottos(LottoGameMoney purchaseMoney) {
        final int manualLottoCount = InputView.getManualLottoCount();
        final LottoCount lottoCount = purchaseMoney.createLottoCount(manualLottoCount);
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(manualLottoCount);

        final List<Lotto> lottos = LottoFactory.createLottos(lottoCount, manualLottoNumbers,
            new RandomLottoNumbersGenerator());
        OutputView.showPurchasedLottos(lottoCount, lottos);

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
