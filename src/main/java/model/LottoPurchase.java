package model;

import static model.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static model.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;

public class LottoPurchase {

    private static final Integer MIN_AMOUNT = 1_000;

    private final Integer amount;

    public static LottoPurchase of(final Integer input) {
        return new LottoPurchase(input);
    }

    public LottoPurchase(Integer amount) {
        validateMinAmount(amount);
        validateAmountUnit(amount);
        this.amount = amount;
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
