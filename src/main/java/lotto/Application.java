package lotto;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.validateLottoNumber;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashSet;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Lotto> lottos = purchaseLottos();
        OutputView.printLottos(lottos);
        WinningNumbers winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);
        WinningStatistics winningStatistics = LottoManager.calculateStatistics(lottos, winningNumbers, bonusNumber);
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

    private static WinningNumbers getWinningNumbers() {
        try {
            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            return new WinningNumbers(winningNumbers);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static int getBonusNumber(final WinningNumbers winningNumbers) {
        try {
            int bonusNumber = InputView.inputBonusNumber();
            validateLottoNumber(bonusNumber);
            winningNumbers.validateBonusNumberDuplicated(bonusNumber);
            return bonusNumber;
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusNumber(winningNumbers);
        }
    }
}
