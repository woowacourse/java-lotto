package domain.player;

import utils.ExceptionMessage;

public class Money {

    static final int MINIMUM_PURCHASE_AMOUNT = 1000;

    private int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    public void deductMoney() {
        amount -= MINIMUM_PURCHASE_AMOUNT;
    }

    public boolean isBiggerThanLottoPrice() {
        return amount >= MINIMUM_PURCHASE_AMOUNT;
    }
}
