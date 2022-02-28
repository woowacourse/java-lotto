package lotto.domain;

import lotto.validator.MoneyValidator;

public class Money {

    private final int money;

    private Money(int money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public static Money generateMoneyByConsole(String consoleInput) {
        return new Money(Integer.parseInt(consoleInput));
    }

    public int getMoney() {
        return money;
    }
}
