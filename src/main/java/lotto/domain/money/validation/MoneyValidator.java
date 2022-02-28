package lotto.domain.money.validation;

import lotto.domain.money.condition.MoneyUnit;
import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;

public class MoneyValidator {

    private MoneyValidator() {
    }

    public static void validateMoney(final int money) {
        verifyMoneyNotZero(money);
        verifyMoneyDivisible(money);
    }

    private static void verifyMoneyNotZero(final int money) {
        if (money == 0) {
            throw new LottoException(MoneyExceptionStatus.MONEY_CANNOT_BE_ZERO);
        }
    }

    private static void verifyMoneyDivisible(final int money) {
        if (MoneyUnit.isNotDivisible(money)) {
            throw new LottoException(MoneyExceptionStatus.MONEY_MUST_BE_DIVISIBLE);
        }
    }

}
