package view;

import exception.ExceptionMessage;

public class InputValidator {

    public static void validateBlank(String value) {
        if (value.isBlank() || value == null) {
            throw new IllegalArgumentException(ExceptionMessage.BLANK_VALUE);
        }
    }

    public static void validateIntegerOverflow(String value) {
        if (value.length() > 10) {
            throw new IllegalArgumentException(ExceptionMessage.INTEGER_OVERFLOW_VALUE);
        }
    }
}
