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
}
