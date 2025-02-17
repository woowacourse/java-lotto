package utils;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private StringParser() {

    }

    public static int parseToNumber(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    //공백 제거 추가
    public static List<Integer> parseToNumbers(String input, String delimiter, String errorMessage) {
        return Arrays.stream(input.split(delimiter))
                .map(token -> parseToNumber(token.trim(), errorMessage))
                .toList();
    }
}
