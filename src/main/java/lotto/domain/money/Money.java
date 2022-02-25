package lotto.domain.money;

import lotto.domain.money.condition.MoneyUnit;
import lotto.domain.money.validator.MoneyValidator;

public class Money {

    private final int money;

    public Money(final int money) {
        MoneyValidator.validateCreditMoney(money);
        this.money = money;
    }

    public int getQuotient() {
        return MoneyUnit.divide(money);
    }

    public int getMoney() {
        return money;
    }

}
