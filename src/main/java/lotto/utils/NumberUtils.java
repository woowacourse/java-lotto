package lotto.utils;

import lotto.domain.errors.ErrorMessage;

public class NumberUtils {
    private NumberUtils() {
    }

    public static int parseNumber(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            ErrorMessage nowErrorMessage = ErrorMessage.NOT_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }
}
