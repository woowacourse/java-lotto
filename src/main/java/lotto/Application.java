package lotto;

import static lotto.Lotto.validateNumberRange;

import java.util.List;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        List<Lotto> lottos = purchaseLottos();
        OutputView.printLottos(lottos);
        WinningLotto winningLotto = getWinningLotto();
        WinningStatistics winningStatistics = winningLotto.calculateStatistics(lottos);
        double returnRate = winningStatistics.calculateReturnRate(lottos.size() * LottoManager.LOTTO_UNIT_PRICE);
        OutputView.printWinningStatistics(winningStatistics, returnRate);
    }

    private static List<Lotto> purchaseLottos() {
        try {
            int purchaseAmount = getPurchaseAmount();
            return LottoManager.purchase(purchaseAmount);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return purchaseLottos();
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
            int bonusNumber = getBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private static Lotto getWinningNumbers() {
        try {
            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            return new Lotto(winningNumbers);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static int getBonusNumber() {
        try {
            int bonusNumber = InputView.inputBonusNumber();
            validateNumberRange(bonusNumber);
            return bonusNumber;
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusNumber();
        }
    }
}
