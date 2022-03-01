package lotto.domain.money;

import lotto.utils.MoneyUnit;

public class Money {

    private final int money;

    public Money(final int money) {
        MoneyValidator.validateMoney(money);
        this.money = money;
    }

    public int getQuotient() {
        return MoneyUnit.divide(money);
    }

    public int getMoney() {
        return money;
    }

}
