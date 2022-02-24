package lotto.domain.credit;

import lotto.domain.credit.condition.CreditUnit;
import lotto.exception.LottoException;
import lotto.exception.credit.CreditMoneyExceptionStatus;

public class Credit {

    private final int money;

    public Credit(final int money) {
        validateMoneyIsDivisible(money);
        this.money = money;
    }

    private void validateMoneyIsDivisible(final int money) {
        if (CreditUnit.isNotDivisible(money)) {
            throw new LottoException(CreditMoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
        }
    }

    public int getQuotient() {
        return CreditUnit.divide(money);
    }

    public int getMoney() {
        return money;
    }

}
