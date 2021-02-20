package lottogame.domain;

import lottogame.utils.CannotBuyLottoException;
import lottogame.utils.InvalidMoneyException;

public class Money {
    private static final int LOTTO_PRICE = 1000;
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

    public int lottoQuantity() {
        int quantity = money / LOTTO_PRICE;
        if (quantity == 0) {
            throw new CannotBuyLottoException();
        }
        return quantity;
    }
}
