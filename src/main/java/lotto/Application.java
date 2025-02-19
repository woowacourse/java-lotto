package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.SystemLottoGenerator;
import lotto.domain.WinningLotto;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        OutputView.printLottos(lottos);
        WinningLotto winningLotto = getWinningLotto();
        WinningStatistics winningStatistics = winningLotto.calculateStatistics(lottos);
        double returnRate = winningStatistics.calculateReturnRate(purchaseAmount);
        OutputView.printWinningStatistics(winningStatistics, returnRate);
    }

    private static List<Lotto> purchaseLottos(final int purchaseAmount) {
        try {
            LottoMachine lottoMachine = new LottoMachine(new SystemLottoGenerator());
            return lottoMachine.purchase(purchaseAmount);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return purchaseLottos(purchaseAmount);
        }
    }

    private static int getPurchaseAmount() {
        try {
            return InputView.inputPurchaseAmount();
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static WinningLotto getWinningLotto() {
        Lotto winningNumbers = getWinningNumbers();
        try {
            LottoNumber bonusNumber = getBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private static Lotto getWinningNumbers() {
        try {
            return new Lotto(InputView.inputWinningNumbers());
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static LottoNumber getBonusNumber() {
        try {
            return new LottoNumber(InputView.inputBonusNumber());
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusNumber();
        }
    }
}
