package common.utils;

public class ValidationUtils {
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
}