package view.util;

import java.util.Arrays;

public class InputUtil {

    public static int[] splitAndChangeToInt(final String inputNumbers, final String delimiter, final String message) {
        String[] splitNumbers = inputNumbers.split(delimiter);
        return Arrays.stream(splitNumbers)
                .mapToInt(number -> checkAndChangeToInt(number, message))
                .toArray();
    }

    public static int checkAndChangeToInt(final String number, final String message) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }
}
