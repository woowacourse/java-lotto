package utils;

import error.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class StringParser {

    private StringParser() {

    }

    public static int parseToNumber(String input, ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    //공백 제거 추가
    public static List<Integer> parseToNumbers(String input, String delimiter, ErrorMessage errorMessage) {
        return Arrays.stream(input.split(delimiter))
                .map(token -> parseToNumber(token.trim(), errorMessage))
                .toList();
    }
}
