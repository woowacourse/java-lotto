package model;

import static constant.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static constant.ExceptionMessage.INVALID_LOTTO_PURCHASE_FORMAT;
import static constant.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.LOTTO_PURCHASE_MIN_AMOUNT;

public class LottoPurchase {

    private final Integer amount;

    public static LottoPurchase of(final String input) {
        validateNullOrBlank(input);
        validateInteger(input);
        return new LottoPurchase(parseInteger(input));
    }

    private LottoPurchase(Integer amount) {
        validateMinAmount(amount);
        validateAmountUnit(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PURCHASE_MIN_AMOUNT;
    }

    private static void validateNullOrBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LOTTO_PURCHASE_FORMAT.getMessage());
        }
    }

    private static Integer parseInteger(String input) {
        return Integer.parseInt(input);
    }

    private void validateMinAmount(Integer amount) {
        if (amount < LOTTO_PURCHASE_MIN_AMOUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_MIN_PURCHASE.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));
        }
    }

    private void validateAmountUnit(Integer amount) {
        if (amount % LOTTO_PURCHASE_MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(INVALID_LOTTO_PURCHASE_UNIT.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));
        }
    }
}
