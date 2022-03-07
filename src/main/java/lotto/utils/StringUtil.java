package lotto.utils;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String EMPTY_ERROR_MESSAGE = "빈 문자를 입력할 수 없습니다.";
    private static final String REQUEST_DELIMITER_MESSAGE = "로 구분된 숫자를 입력해주세요.";

    private StringUtil() {
    }

    public static List<Integer> splitToIntegers(String input, String delimiter) {
        checkEmpty(input);
        List<String> splitInputs = List.of(input.split(delimiter, -1));
        try {
            return splitInputs.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("\"" + delimiter + "\"" + REQUEST_DELIMITER_MESSAGE);
        }
    }

    private static void checkEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }
}
