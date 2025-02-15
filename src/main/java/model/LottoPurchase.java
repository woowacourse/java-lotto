package model;


import exception.LottoPurchaseExceptionType;

public class LottoPurchase {

    public static final int MIN_AMOUNT = 1_000;

    private final int amount;

    public static LottoPurchase of(final int input) {
        return new LottoPurchase(input);
    }

    public LottoPurchase(int amount) {
        validateMinAmount(amount);
        validateAmountUnit(amount);
        this.amount = amount;
    }

    private void validateMinAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(
                    LottoPurchaseExceptionType.INVALID_LOTTO_MIN_PURCHASE.getMessage(MIN_AMOUNT));
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(
                    LottoPurchaseExceptionType.INVALID_LOTTO_PURCHASE_UNIT.getMessage(MIN_AMOUNT));
        }
    }

    public int getAmount() {
        return amount;
    }
}
