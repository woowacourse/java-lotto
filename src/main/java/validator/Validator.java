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

    public static void checkInvalidForm(String input, String regexPattern, String errorMessage) {
        if (!input.matches(regexPattern)) {
            throw new IllegalArgumentException(errorMessage);
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
