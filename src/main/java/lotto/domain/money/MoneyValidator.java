package lotto.domain.money;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class MoneyValidator {

    private MoneyValidator() {
    }

    public static void validateMoney(final int money) {
        verifyMoneyPositive(money);
        verifyMoneyDivisible(money);
    }

    private static void verifyMoneyPositive(final int money) {
        if (money <= 0) {
            throw new LottoException(LottoExceptionStatus.MONEY_MUST_BE_POSITIVE);
        }
    }

    private static void verifyMoneyDivisible(final int money) {
        if (MoneyUnit.isNotDivisible(money)) {
            throw new LottoException(LottoExceptionStatus.MONEY_MUST_BE_DIVISIBLE);
        }
    }

}
