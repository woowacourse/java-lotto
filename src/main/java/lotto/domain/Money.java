package lotto.domain;

import lotto.exception.InvalidMoneyException;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    static void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new InvalidMoneyException(money);
        }
    }

    public int calculateLottoTicketCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
