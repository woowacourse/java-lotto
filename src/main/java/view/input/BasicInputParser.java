package view.input;

import constans.ErrorType;
import java.util.Arrays;
import java.util.List;

public class BasicInputParser implements InputParser {

    private static final String WINNING_NUMBERS_DELIMITER = ",";

    @Override
    public int parsePurchaseAmount(final String purchaseAmountText) {
        return parseInt(ErrorType.PURCHASE_AMOUNT_INVALID_INPUT, purchaseAmountText);
    }

    @Override
    public List<Integer> parseWinningNumbers(final String winningNumbersText) {
        return Arrays.stream(winningNumbersText.split(WINNING_NUMBERS_DELIMITER))
            .map(text -> parseInt(ErrorType.WINNING_NUMBERS_INVALID_INPUT, text))
            .toList();
    }

    @Override
    public int parseBonusBall(final String bonusBallText) {
        return parseInt(ErrorType.BONUS_BALL_INVALID_INPUT, bonusBallText);
    }

    private int parseInt(
        final ErrorType errorType,
        final String text
    ) {
        try {
            return Integer.parseInt(text.strip());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(errorType.getMessage());
        }
    }
}
