package lotto.util;

import lotto.exception.InputTypeException;

public class InputConvertor {

    public static int toNaturalNumber(String input) {
        try {
            int inputInt = Integer.parseInt(input);
            InputValidator.checkNaturalNumber(inputInt);
            return inputInt;
        } catch (NumberFormatException e) {
            throw new InputTypeException(InputTypeException.INPUT_ONLY_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }
}
