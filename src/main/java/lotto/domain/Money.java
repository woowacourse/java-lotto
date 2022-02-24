package lotto.domain;

import lotto.utils.Validation;

public class Money {

    public static final int BASIC_LOTTO_MONEY = 1000;

    private final int money;

    public Money(final String money) {
        Validation.checkInputMoney(money);
        this.money = Integer.parseInt(money);
    }

    public int getMoney() {
        return money;
    }
}
