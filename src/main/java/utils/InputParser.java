package utils;

import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<Integer> parseAndCreateWinningNumbers(String inputWinningNumbers) {
        List<String> parsedNumbers = List.of(inputWinningNumbers.split(DELIMITER));
        return parsedNumbers.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

}
