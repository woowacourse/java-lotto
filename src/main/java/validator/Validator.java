package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    // 입력이 빈 입력인 경우
    public static void validateEmptyInput(String input) {
        checkNullInput(input, ErrorMessages.NULL_INPUT.getMessage());
        checkEmptyInput(input, ErrorMessages.EMPTY_STRING_INPUT.getMessage());
        checkWhitespaceOnlyInput(input, ErrorMessages.WHITESPACE_ONLY_INPUT.getMessage());
    }

    public static void checkNullInput(String input, String errorMessage) {
        if (input == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkEmptyInput(String input, String errorMessage) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkWhitespaceOnlyInput(String input, String errorMessage) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 주어진 정규표현식에 따른 유효성 검사
    public static void validateInvalidForm(String input, String regexPattern, String errorMessage) {
        if (!input.matches(regexPattern)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 주어진 범위에 따른 유효성 검사 (이상 값, 이하 값)
    public static void validateOutOfRange(int number, int rangeStart, int rangeEnd, String errorMessage) {
        if (number < rangeStart || number > rangeEnd) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // set의 특성을 활용한 리스트 내의 중복된 요소 판별
    public static <T> boolean isDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return false; // null 또는 빈 리스트는 중복이 없다고 간주
        }

        Set<T> set = new HashSet<>(list);
        return set.size() != list.size();
    }
}