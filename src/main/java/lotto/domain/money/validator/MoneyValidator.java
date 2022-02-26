package lotto.domain.money.validator;

import lotto.domain.money.condition.MoneyUnit;
import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;

public class MoneyValidator {

    private static final MoneyValidator INSTANCE = new MoneyValidator();

    private MoneyValidator() {
    }

    public static void validateCreditMoney(final int money) {
        INSTANCE.validateMoneyIsZero(money);
        INSTANCE.validateMoneyIsDivisible(money);
    }

    private void validateMoneyIsZero(final int money) {
        if (money == 0) {
            throw new LottoException(MoneyExceptionStatus.MONEY_IS_ZERO);
        }
    }

    private void validateMoneyIsDivisible(final int money) {
        if (MoneyUnit.isNotDivisible(money)) {
            throw new LottoException(MoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
        }
    }

}
