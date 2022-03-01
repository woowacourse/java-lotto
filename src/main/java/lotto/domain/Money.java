package lotto.domain;

import lotto.validator.MoneyValidator;

public class Money {

    private final int money;

    private Money(int money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public static Money generateMoneyByString(String money) {
        return new Money(Integer.parseInt(money));
    }

    public int getMoney() {
        return money;
    }
}
