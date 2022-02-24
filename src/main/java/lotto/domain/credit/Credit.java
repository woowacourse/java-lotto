package lotto.domain.credit;

import lotto.exception.LottoException;
import lotto.exception.credit.CreditMoneyExceptionStatus;

public class Credit {

    private static final int CREDIT_BASE_UNIT = 1000;

    private final int money;

    public Credit(final int money) {
        validateMoneyIsDivisible(money);
        this.money = money;
    }

    private void validateMoneyIsDivisible(final int money) {
        if (money % CREDIT_BASE_UNIT != 0) {
            throw new LottoException(CreditMoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
        }
    }

    public int getQuotient() {
        return money / CREDIT_BASE_UNIT;
    }

    public int getMoney() {
        return money;
    }

}
