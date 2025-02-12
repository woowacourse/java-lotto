package utils;

public class Validator {
    public static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateRange(int input, int max, int min) {
        if (input > max || input < min) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateDivide(int input, int division) {
        if (input % division != 0) {
            throw new IllegalArgumentException();
        }
    }
}
