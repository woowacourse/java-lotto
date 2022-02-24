package utils;

import java.util.List;

public class Validator {

    private static final String ERROR_NULL_OR_EMPTY_MESSAGE = "null 또는 빈값을 입력할 수 없습니다.";
    private static final String POSITIVE_NUMBER_FORMAT = "^[1-9]([0-9]*)$";
    private static final String ERROR_FORMAT_MESSAGE = "입력한 값이 숫자의 형태가 아닙니다.";
    private static final String ERROR_DUPLICATION_MESSAGE = "중복값을 입력할 수 없습니다.";

    public static void checkNullOrEmpty(final String inputNumber) {
        if (inputNumber == null || inputNumber.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NULL_OR_EMPTY_MESSAGE);
        }
    }

    public static void checkFormat(final String input) {
        if (!input.matches(POSITIVE_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(ERROR_FORMAT_MESSAGE);
        }
    }

    public static void checkDuplication(final List<String> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_MESSAGE);
        }
    }
}
