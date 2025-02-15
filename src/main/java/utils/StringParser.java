package utils;

import exception.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public class StringParser {

    private StringParser() {

    }

    public static int parseToNumber(final String input, final ExceptionMessage exceptionMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(exceptionMessage.getMessage());
        }
    }

    public static List<Integer> parseToNumbers(final String input, final String delimiter,
                                               final ExceptionMessage exceptionMessage) {
        return Arrays.stream(input.split(delimiter))
                .map(token -> parseToNumber(token, exceptionMessage))
                .toList();
    }
}
