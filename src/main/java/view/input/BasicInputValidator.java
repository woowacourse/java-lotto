package view.input;

import constans.ErrorType;
import java.util.List;
import java.util.regex.Pattern;

public class BasicInputValidator implements InputValidator {
    private static final String WINNING_NUMBERS_REGEX_TEXT = "";
    private static final Pattern WINNING_NUMBERS_REGEX = Pattern.compile(WINNING_NUMBERS_REGEX_TEXT);

    @Override
    public void validatePurchaseAmount(final int purchaseAmount) {
        validatePurchaseAmountRange(purchaseAmount);
    }

    @Override
    public void validateWinningNumbersText(final String winningNumbersText) {
        validateWinningNumbersFormat(winningNumbersText);
    }

    @Override
    public void validateWinningNumbers(final List<Integer> winningNumbers) {
        validateWinningNumbersDuplication(winningNumbers);
    }

    private void validateWinningNumbersDuplication(final List<Integer> winningNumbers) {
        if (winningNumbers.stream().distinct().toList().size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }
    }


    private void validateWinningNumbersFormat(final String winningNumbersText) {
        if (!WINNING_NUMBERS_REGEX.matcher(winningNumbersText).matches()) {
            throw new IllegalArgumentException(ErrorType.WINNING_NUMBERS_FORMAT_INVALID.getMessage());
        }
    }

    private void validatePurchaseAmountRange(final int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorType.PURCHASE_AMOUNT_RANGE_INVALID.getMessage());
        }
    }
}
