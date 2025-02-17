package parser;

import static domain.exception.ExceptionMessage.INVALID_FORMAT;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final String DELIMITER = ",";

    public static List<Integer> lottoParser(String inputNumbers) {
        try {
            return Arrays.stream(inputNumbers.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
