package lotto.validator;

import lotto.exception.MoneyException;

public class MoneyValidator {

    private static final int UNIT_SIZE = 1000;

    public static void validate(long input) {
        checkPositive(input);
        checkUnit(input);
    }

    private static void checkPositive(long input) {
        if (input < 0) {
            throw new MoneyException(MoneyException.MONEY_ONLY_POSITIVE_NUMBER_ERROR_MESSAGE);
        }
    }

    private static void checkUnit(long input) {
        if (!isCorrectUnit(input)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectUnit(long input) {
        return input % UNIT_SIZE == 0;
    }
}
