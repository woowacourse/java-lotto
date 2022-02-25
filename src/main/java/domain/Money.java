package domain;

import validator.MoneyValidator;

public class Money {

    private final int money;
    private final int MONEY_UNIT = 1000;

    public Money(int money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public int toLottoCount() {
        return money / MONEY_UNIT;
    }

    public int get() {
        return money;
    }
}