package domain;

import validator.MoneyValidator;

public class Money {

    private final int money;
    private final int UNIT = 1000;

    public Money(int money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public int toLottoCount() {
        return money / UNIT;
    }

    public int get() {
        return money;
    }
}