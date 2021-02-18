package lotto.domain;

import lotto.exception.MoneyException;

public class Money {
    int money;

    public Money(int money) {
        validateMoneyValue(money);
        this.money = money;
    }

    private void validateMoneyValue(int money) {
        if (money < 1000) {
            throw new MoneyException();
        }
    }

    public int getAffordableLottoTickets(int lottoPrice) {
        return money / lottoPrice;
    }
}
