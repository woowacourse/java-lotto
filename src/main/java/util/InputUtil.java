package util;

import java.util.Arrays;

public class InputUtil {

    private static final String LOTTO_NUMBER_DELIMITER = ", |,";

    public static int[] splitAndChangeToInt(final String inputNumbers, final String message) {
        String[] splitNumbers = inputNumbers.split(LOTTO_NUMBER_DELIMITER);
        return Arrays.asList(splitNumbers).stream()
                .mapToInt(number -> validateAndChangeToInt(number, message))
                .toArray();
    }

    public static int validateAndChangeToInt(final String number, final String message) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }
}
