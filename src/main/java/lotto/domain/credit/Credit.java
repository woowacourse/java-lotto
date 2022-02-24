package lotto.domain.credit;

import lotto.domain.credit.condition.CreditUnit;
import lotto.domain.credit.validator.CreditMoneyValidator;

public class Credit {

    private final int money;

    public Credit(final int money) {
        CreditMoneyValidator.validateCreditMoney(money);
        this.money = money;
    }

    public int getQuotient() {
        return CreditUnit.divide(money);
    }

    public int getMoney() {
        return money;
    }

}
