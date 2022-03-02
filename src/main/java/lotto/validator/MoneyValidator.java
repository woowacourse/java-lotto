package lotto.validator;

import lotto.exception.MoneyException;

public class MoneyValidator {

    private static final int UNIT_SIZE = 1000;

    public static void validate(long money) {
        checkPositive(money);
        checkUnit(money);
    }

    private static void checkPositive(long input) {
        if (input < 0) {
            throw new MoneyException(MoneyException.MONEY_ONLY_POSITIVE_NUMBER_ERROR_MESSAGE);
        }
    }

    private static void checkUnit(long money) {
        if (!isCorrectUnit(money)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectUnit(long money) {
        return money % UNIT_SIZE == 0;
    }
}
