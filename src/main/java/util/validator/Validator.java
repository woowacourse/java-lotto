package util.validator;

import static util.constant.Message.INPUT_NON_NUMERIC_ERROR;
import static util.constant.Message.INPUT_NULL_ERROR;

public class Validator {

    public static void validateNotNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
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
