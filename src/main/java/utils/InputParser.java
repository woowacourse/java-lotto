package utils;

import static error.ErrorMessage.INVALID_LOTTO_NUMBER_INPUT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<Integer> parseAndCreateWinningNumbers(String inputWinningNumbers) {
        String[] parsedNumbers = inputWinningNumbers.split(DELIMITER);

        validateParsedNumbers(Arrays.asList(parsedNumbers));

        return Arrays.stream(parsedNumbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validateParsedNumbers(List<String> parsedNumbers) {
        if (parsedNumbers.stream()
                .anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_INPUT.getMessage());
        }
    }
}
