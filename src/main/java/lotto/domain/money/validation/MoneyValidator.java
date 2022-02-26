package lotto.domain.money.validation;

import lotto.domain.money.condition.MoneyUnit;
import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;

public class MoneyValidator {

    private static final MoneyValidator INSTANCE = new MoneyValidator();

    private MoneyValidator() {
    }

    public static void validateCreditMoney(final int money) {
        INSTANCE.verifyMoneyIsNotZero(money);
        INSTANCE.verifyMoneyIsDivisible(money);
    }

    private void verifyMoneyIsNotZero(final int money) {
        if (money == 0) {
            throw new LottoException(MoneyExceptionStatus.MONEY_IS_ZERO);
        }
    }

    private void verifyMoneyIsDivisible(final int money) {
        if (MoneyUnit.isNotDivisible(money)) {
            throw new LottoException(MoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
        }
    }

}
