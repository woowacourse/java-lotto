package view.input;

import java.util.List;

public interface InputValidator {
    void validatePurchaseAmount(int purchaseAmount);

    void validateWinningNumbersText(String winningNumbersText);

    void validateWinningNumbers(List<Integer> winningNumbers);
}
