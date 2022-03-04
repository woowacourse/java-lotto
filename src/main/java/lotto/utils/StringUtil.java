package lotto.utils;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String EMPTY_ERROR_MESSAGE = "빈 문자를 입력할 수 없습니다.";
    private static final String REQUEST_DELIMITER_MESSAGE = ",(콤마)와 공백으로 구분된 숫자 6자리를 입력해주세요. ex) 1, 2, 3, 4, 5, 6";
    private static final String DELIMITER = ", ";

    public static List<Integer> splitToIntegers(String input) {
        checkEmpty(input);
        List<String> splitInputs = List.of(input.split(DELIMITER, -1));
        try {
            return splitInputs.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(REQUEST_DELIMITER_MESSAGE);
        }
    }

    private static void checkEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }
}
