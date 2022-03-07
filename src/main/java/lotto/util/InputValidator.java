package lotto.util;

import lotto.exception.InputTypeException;

public class InputValidator {

    public static void checkInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputTypeException(InputTypeException.INPUT_ONLY_INTEGER_ERROR_MESSAGE);
        }
    }
}
