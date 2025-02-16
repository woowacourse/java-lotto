package util;

import exception.ExceptionMessage;
import java.util.List;

public class Parser {

    public static int parseOneNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_INTEGER_VALUE);
        }
    }

    public static List<Integer> parseNumbers(List<String> values) {
        try {
            return values.stream()
                    .map(value -> {
                        validateIntegerOverFlow(value);
                        return Integer.parseInt(value.trim());
                    })
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_INTEGER_VALUE);
        }
    }

    private static void validateIntegerOverFlow(String value) {
        if (value.length() > 10) {
            throw new IllegalArgumentException(ExceptionMessage.INTEGER_OVERFLOW_VALUE);
        }
    }
}
