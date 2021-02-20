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

    public float divide(int totalPrizeMoney) {
        return (float) totalPrizeMoney / money;
    }

    int buyLotto(int lottoPrice) {
        return money / lottoPrice;
    }
}
