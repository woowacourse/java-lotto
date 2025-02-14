package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberParser {
    private static final String ERROR_NUMBER_FORMAT = "숫자만 입력 가능합니다.";
    private static final String ERROR_DELIMITER_FORMAT = "쉼표(,)로 구분된 숫자를 입력해주세요.";

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT);
        }
    }

    public static List<Integer> parseFromCSV(String input) {
        final List<Integer> parsedNumbers = new ArrayList<>();
        final List<String> inputNumbers = Arrays.stream(input.split(",")).map(String::trim).toList();
        inputNumbers.forEach(element -> {
            try {
                parsedNumbers.add(parse(element));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ERROR_DELIMITER_FORMAT);
            }
        });
        return parsedNumbers;
    }
}
