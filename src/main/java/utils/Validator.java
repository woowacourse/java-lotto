package utils;

public class Validator {

    public static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateRange(int number, int min, int max) {
        if (number > max || number < min) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateDivisibility(int number, int division) {
        if (number % division != 0) {
            throw new IllegalArgumentException();
        }
    }
}
