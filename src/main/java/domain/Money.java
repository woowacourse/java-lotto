package domain;

import utils.ExceptionMessage;

public class Money {

    public static final int LOTTO_PRICE = 1000;

    private int amount;

    public Money(int amount) {
        validateAmount(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_1000_UNIT);
        }
    }

    public boolean isBiggerThanLottoPrice() {
        return amount >= LOTTO_PRICE;
    }

    public int calculatePurchasableLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
