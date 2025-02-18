package util;

import static util.constant.Message.INPUT_NON_NUMERIC_ERROR;
import static util.constant.Message.INPUT_NULL_ERROR;

public class Validator {

    private Validator() {
    }

    public static void inputValidatorIsNull(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INPUT_NULL_ERROR);
        }
    }

    public static void inputValidatorParseInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(INPUT_NON_NUMERIC_ERROR);
        }
    }
}
