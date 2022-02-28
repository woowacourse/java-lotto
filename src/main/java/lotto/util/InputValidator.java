package lotto.util;

import lotto.exception.InputTypeException;

public class InputValidator {

    public static void checkNaturalNumber(int number) {
        if (number <= 0) {
            throw new InputTypeException(InputTypeException.INPUT_ONLY_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }
}
