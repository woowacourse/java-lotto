package global.utils;

import static global.constant.ErrorMessage.NUMERIC_INPUT_ONLY_MESSAGE;
import static global.constant.ErrorMessage.RANGE_INPUT_ONLY_MESSAGE;

public final class Validator {

    private Validator() {
    }

    public static void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_INPUT_ONLY_MESSAGE.getMessage());
        }
    }

    public static void validateNumberRange(final int number, final int min, final int max) {
        if (number > max || number < min) {
            throw new IllegalArgumentException(RANGE_INPUT_ONLY_MESSAGE.getMessage());
        }
    }
}
