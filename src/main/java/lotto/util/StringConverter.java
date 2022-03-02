package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringConverter {

    private static final String DELIMITER = ",";

    private StringConverter() {
    }

    public static List<Integer> toInts(final String input) {
        return Arrays.stream(trim(split(input)))
                .map(StringConverter::toInt)
                .collect(Collectors.toList());
    }

    private static String[] split(final String input) {
        return input.split(DELIMITER);
    }

    private static String[] trim(final String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .toArray(String[]::new);
    }

    public static int toInt(final String input) {
        validateNullOrBlank(input);
        validateNumberFormat(input);
        return Integer.parseInt(input);
    }

    private static void validateNullOrBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다");
        }
    }

    private static void validateNumberFormat(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야합니다");
        }
    }
}
