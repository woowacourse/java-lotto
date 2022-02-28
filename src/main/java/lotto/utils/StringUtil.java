package lotto.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    private static final String SPLIT_DELIMITER = ",";
    private static final String PARSE_INT_ERROR_MESSAGE = "숫자만 허용됩니다.";

    private StringUtil() {
    }

    public static List<Integer> toIntegers(String text) {
        List<String> strings = getSplit(text);
        List<Integer> numbers = new ArrayList<>();
        try {
            numbers = strings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
        }
        return numbers;
    }

    private static List<String> getSplit(String text) {
        return Arrays.stream(text.split(SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int toInt(final String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(PARSE_INT_ERROR_MESSAGE);
        }
    }
}
