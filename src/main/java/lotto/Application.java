package lotto;

import static lotto.Lotto.LOTTO_SIZE;
import static lotto.Lotto.validateLottoNumber;

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
        } catch (final NumberFormatException e) {
            OutputView.printErrorMessage("구입금액은 숫자여야 합니다.");
            return getPurchaseAmount();
        }
    }

    private static WinningNumbers getWinningNumbers() {
        try {
            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            validateWinningNumbers(winningNumbers);
            return new WinningNumbers(winningNumbers);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static void validateWinningNumbers(final List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE + "개의 고유한 번호를 입력해야 합니다.");
        }

        for (final int winningNumber : winningNumbers) {
            validateLottoNumber(winningNumber);
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
