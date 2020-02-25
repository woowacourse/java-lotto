package lotto.domain;

public class NumberValidator {

    private NumberValidator() { /* prevent creating instance */ }

    public static void validateIfEmptyValueThenInvokeException(String number, String message) {
        if (number == null || number.trim().isEmpty()) {
            throw new RuntimeException(message);
        }
    }

    public static void validateIfNotNumberThenInvokeException(String number, String message) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new RuntimeException(message);
        }
    }
}
