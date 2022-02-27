package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringConverter {

    private StringConverter() {
    }

    public static List<Integer> toInts(String input, String delimiter) {
        return Arrays.stream(trim(split(input, delimiter)))
                .map(StringConverter::toInt)
                .collect(Collectors.toList());
    }

    private static String[] split(String input, String delimiter) {
        return input.split(delimiter);
    }

    private static String[] trim(String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .toArray(String[]::new);
    }

    public static int toInt(String input) {
        validateNullOrBlank(input);
        validateNumberFormat(input);
        return Integer.parseInt(input);
    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다");
        }
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야합니다");
        }
    }
}
