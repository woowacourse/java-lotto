package util;

import static error.ErrorMessage.INPUT_MUST_BE_NUMERIC;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    public static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_MUST_BE_NUMERIC.getMessage());
        }
    }

    public static List<Integer> convertToIntegers(String input) {
        String[] rawNumbers = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String rawNumber : rawNumbers) {
            int number = convertToInteger(rawNumber);
            numbers.add(number);
        }
        return numbers;
    }
}
