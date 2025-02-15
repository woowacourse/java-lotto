package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private static final String NUMBER_ONLY_REGEX = "^[0-9]+$";

    public static void validateEmptyInput(String input) {
        checkNullInput(input, "입력이 null 입니다.");
        checkEmptyInput(input, "입력이 빈 문자열입니다.");
        checkWhitespaceOnlyInput(input, "입력이 공백으로만 구성되어 있습니다.");
    }

    private static void checkNullInput(String input, String errorMessage) {
        if (input == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void checkEmptyInput(String input, String errorMessage) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void checkWhitespaceOnlyInput(String input, String errorMessage) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkInvalidNumberForm(String input) {
        if (!input.matches(NUMBER_ONLY_REGEX)) {
            throw new IllegalArgumentException("입력된 문자가 숫자가 아닙니다.");
        }
    }

    public static void checkOutOfRange(int number, int rangeStart, int rangeEnd, String errorMessage) {
        if (number < rangeStart || number > rangeEnd) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> boolean isDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }

        Set<T> set = new HashSet<>(list);
        return set.size() != list.size();
    }
}
