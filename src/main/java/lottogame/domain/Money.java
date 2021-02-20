package lottogame.domain;

import lottogame.utils.InvalidMoneyException;

public class Money {
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new InvalidMoneyException();
        }
    }

    public int getMoney() {
        return this.money;
    }

    public int buyLotto(int lottoPrice) {
        return money / lottoPrice;
    }
}
