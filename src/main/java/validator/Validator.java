package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    public static void validateEmptyInput(String input) {
        checkNullInput(input, ErrorMessages.NULL_INPUT.getMessage());
        checkEmptyInput(input, ErrorMessages.EMPTY_STRING_INPUT.getMessage());
        checkWhitespaceOnlyInput(input, ErrorMessages.WHITESPACE_ONLY_INPUT.getMessage());
    }

    // 입력이 null 인 경우
    public static void checkNullInput(String input, String errorMessage) {
        if (input == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 입력이 빈 문자열인 경우
    public static void checkEmptyInput(String input, String errorMessage) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 입력이 공백으로 이루어진 경우
    public static void checkWhitespaceOnlyInput(String input, String errorMessage) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 입력이 유효하지 않는 경우 - 숫자 판별
    public static void checkInvalidForm(String input, String regexPattern, String errorMessage) {
        if (!input.matches(regexPattern)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // -- 로또 번호 --
    // 숫자가 주어진 범위를 넘는 경우 (이상 값, 이하 값)
    public static void checkOutOfRange(int number, int rangeStart, int rangeEnd, String errorMessage) {
        if (number < rangeStart || number > rangeEnd) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 리스트 내에 중복된 요소가 있는지 판단
    public static <T> boolean isDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return false; // null 또는 빈 리스트는 중복이 없다고 간주
        }

        Set<T> set = new HashSet<>(list); // 리스트를 Set 으로 변환
        return set.size() != list.size(); // Set 크기와 리스트 크기를 비교
    }
}
