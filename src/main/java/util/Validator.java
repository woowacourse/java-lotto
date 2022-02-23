package util;

public class Validator {

    public static void validateNegativePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateWinningNumberInput(String[] winningNumbers) {
        if (winningNumbers.length != 6) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
