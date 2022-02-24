package lotto.domain.credit.validator;

import lotto.domain.credit.condition.CreditUnit;
import lotto.exception.LottoException;
import lotto.exception.credit.CreditMoneyExceptionStatus;

public class CreditMoneyValidator {

    private static final CreditMoneyValidator INSTANCE = new CreditMoneyValidator();

    private CreditMoneyValidator() {
    }

    public static void validateCreditMoney(final int ballNumber) {
        INSTANCE.validateMoneyIsDivisible(ballNumber);
    }

    private void validateMoneyIsDivisible(final int money) {
        if (CreditUnit.isNotDivisible(money)) {
            throw new LottoException(CreditMoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
        }
    }

}
