package lotto.util;

import lotto.constant.ErrorMessage;

public class InputValidator {

    public static void validateNullOrBlank(final String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CANNOT_BE_NULL);
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CANNOT_BE_BLANK);
        }
    }
}
