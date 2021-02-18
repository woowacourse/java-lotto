package lotto.domain;

import lotto.controller.LottoStore;
import lotto.exception.MoneyException;

public class Money {
    private final int money;

    public Money(int money) {
        validateMoneyValue(money);
        this.money = money;
    }

    private void validateMoneyValue(int money) {
        if (money < LottoStore.LOTTO_PRICE) {
            throw new MoneyException();
        }
    }

    public int getAffordableLottoTickets() {
        return money / LottoStore.LOTTO_PRICE;
    }
}
