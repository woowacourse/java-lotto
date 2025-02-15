package utils;

import exception.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class StringParser {

    private StringParser() {

    }

    public static int parseToNumber(final String input, final ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    public static List<Integer> parseToNumbers(final String input, final String delimiter,
                                               final ErrorMessage errorMessage) {
        return Arrays.stream(input.split(delimiter))
                .map(token -> parseToNumber(token, errorMessage))
                .toList();
    }
}
