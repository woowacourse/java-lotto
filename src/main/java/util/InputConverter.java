package util;

import static error.ErrorMessage.INPUT_MUST_BE_NUMERIC;
import static error.ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    public static int convertToInteger(String input) {
        try {
            int value = Integer.parseInt(input.trim());
            validateNegativeValue(value);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_MUST_BE_NUMERIC.getMessage());
        }
    }

    public static List<Integer> convertToList(String input) {
        String[] rawNumbers = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String rawNumber : rawNumbers) {
            int number = convertToInteger(rawNumber);
            numbers.add(number);
        }
        return numbers;
    }

    private static void validateNegativeValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_NOT_ALLOWED.getMessage());
        }
    }
}
