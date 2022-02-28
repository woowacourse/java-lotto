package lotto.domain.money;

import lotto.domain.money.condition.MoneyUnit;
import lotto.domain.money.validation.MoneyValidator;

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
