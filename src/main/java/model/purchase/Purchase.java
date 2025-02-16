package model.purchase;

import exception.ExceptionMessage;

public class Purchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_PURCHASE_AMOUNT = 1000;
    private static final int MAX_PURCHASE_AMOUNT = 100000;

    private final int amount;

    public Purchase(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        if (amount < MIN_PURCHASE_AMOUNT || amount > MAX_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_PURCHASE_AMOUNT_RANGE);
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_DIVISIBLE_PURCHASE_AMOUNT);
        }
    }
}
