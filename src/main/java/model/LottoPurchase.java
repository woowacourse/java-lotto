package model;

import static constant.LottoConstant.LOTTO_PURCHASE_MIN_AMOUNT;
import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.message.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_FORMAT;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;
import static constant.pattern.InputPattern.INTEGER_PATTERN;

import util.Parser;

public class LottoPurchase {

    private final int amount;

    public static LottoPurchase of(final String input) {
        validateNullOrBlank(input, INVALID_INPUT_NULL_OR_BLANK.getMessage());
        validateInteger(input, INVALID_LOTTO_PURCHASE_FORMAT.getMessage());
        int amount = Parser.convertStringToInteger(input);

        return new LottoPurchase(amount);
    }

    private LottoPurchase(final int amount) {
        validateMinimum(amount, LOTTO_PURCHASE_MIN_AMOUNT,
                INVALID_LOTTO_MIN_PURCHASE.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));
        validateUnit(amount, LOTTO_PURCHASE_MIN_AMOUNT,
                INVALID_LOTTO_PURCHASE_UNIT.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));

        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PURCHASE_MIN_AMOUNT;
    }

    private static void validateNullOrBlank(final String input, final String errorMessage) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateInteger(final String input, final String errorMessage) {
        if (!input.matches(INTEGER_PATTERN.getContent())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateMinimum(final int number, final int minimum, final String errorMessage) {
        if (number < minimum) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateUnit(final int number, final int unit, final String errorMessage) {
        if (number % unit != 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
