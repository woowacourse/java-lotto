package domain.player;

import utils.ExceptionMessage;

public class Money {

    public static final int ONE_LOTTO_PRICE = 1000;
    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

    private int initialPrice;
    private int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.initialPrice = amount;
        this.amount = amount;
    }

    public int determineQuantity() {
        return amount / MINIMUM_PURCHASE_AMOUNT;
    }

    private void validateAmount(int amount) {
        if (amount < ONE_LOTTO_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    public void isEnoughMoney(int manualQuantity) {
        if (amount < manualQuantity * ONE_LOTTO_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ENOUGH_MONEY);
        }
    }

    public void subtractAmount() {
        amount -= 1000;
    }

    public int getAmount() {
        return amount;
    }

    public int getInitialPrice() {
        return initialPrice;
    }
}
