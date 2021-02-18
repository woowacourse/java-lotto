package lotto.domain;

import lotto.exception.MoneyException;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        validateMoneyValue(money);
        this.money = money;
    }

    private void validateMoneyValue(int money) {
        if (money < LOTTO_PRICE) {
            throw new MoneyException();
        }
    }

    public int getAffordableLottoTickets() {
        return money / LOTTO_PRICE;
    }
}
