package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<Integer> parseAndCreateWinningNumbers(String inputWinningNumbers) {
        return Arrays.stream(inputWinningNumbers.split(DELIMITER))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

}
