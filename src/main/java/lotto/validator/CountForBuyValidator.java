package lotto.validator;

import lotto.exception.CountForBuyException;

public class CountForBuyValidator {

    public static void validate(int count) {
        checkPositive(count);
    }

    private static void checkPositive(int count) {
        if (count < 0) {
            throw new CountForBuyException(CountForBuyException.COUNT_FOR_BUY_ONLY_POSITIVE_ERROR_MESSAGE);
        }
    }
}
