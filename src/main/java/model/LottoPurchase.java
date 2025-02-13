package model;

import static model.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static model.ExceptionMessage.INVALID_LOTTO_PURCHASE_TYPE;
import static model.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;

public class LottoPurchase {

    private static final Integer MIN_AMOUNT = 1_000;

    private final Integer amount;

    public static LottoPurchase of(final String input) {
        validateInteger(input);
        return new LottoPurchase(Integer.parseInt(input));
    }

    public LottoPurchase(Integer amount) {
        validateMinAmount(amount);
        validateAmountUnit(amount);
        this.amount = amount;
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LOTTO_PURCHASE_TYPE.getMessage());
        }
    }

    private void validateMinAmount(Integer amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_MIN_PURCHASE.getMessage(MIN_AMOUNT));
        }
    }

    private void validateAmountUnit(Integer amount) {
        if (amount % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(INVALID_LOTTO_PURCHASE_UNIT.getMessage(MIN_AMOUNT));
        }
    }

    public Integer getAmount() {
        return amount;
    }
}
