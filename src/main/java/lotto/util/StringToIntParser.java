package lotto.util;

import lotto.constant.ErrorMessage;

import java.util.Arrays;
import java.util.List;

public class StringToIntParser {

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_OVER_MAXIMUM_VALUE_OF_INTEGER);
        }
    }

    public static List<Integer> parseTokens(final String[] tokens) {
        return Arrays.stream(tokens)
                .map(String::trim)
                .map(StringToIntParser::parseInt)
                .toList();
    }
}
