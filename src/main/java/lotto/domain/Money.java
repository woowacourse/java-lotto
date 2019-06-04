package lotto.domain;

import lotto.exception.OutOfLottoPurchaseAmountBoundException;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PRICE = 1_000_000_000;

    private final int money;

    public Money(final int money) {
        checkInvalidMoney(money);
        this.money = money;
    }

    int calculateMaxNumberOfPurchase() {
        return money / LOTTO_PRICE;
    }

    boolean canBuy(int count) {
        return count * LOTTO_PRICE <= money;
    }

    private void checkInvalidMoney(final int money) {
        if (money < LOTTO_PRICE || money > MAX_LOTTO_PRICE) {
            throw new OutOfLottoPurchaseAmountBoundException();
        }
    }
}
