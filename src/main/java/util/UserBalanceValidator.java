package util;

import static constant.ExceptionMessages.INVALID_CURRENCY_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;

public class UserBalanceValidator {

    public static int validateUserBalance(String value) {
        int balance = validateAndParseNumber(value);
        validateBalance(balance);
        return balance;
    }

    private static void validateBalance(int balance) {
        if (!(balance > 1000 && balance % 1000 == 0)) {
            throw new IllegalArgumentException(INVALID_CURRENCY_EXCEPTION_MESSAGE);
        }
    }

    private static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
