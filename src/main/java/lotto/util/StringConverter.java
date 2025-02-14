package lotto.util;

import java.util.Arrays;
import java.util.List;

public class StringConverter {

    public static List<Integer> parseToIntList(String input, String delimiter) {
        List<String> result = Arrays.stream(input.split(delimiter)).toList();
        return result.stream()
                .map(s -> Integer.parseInt(s.strip()))
                .toList();
    }

    public static int parseToInt(String input, ErrorHandler errorCode) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw errorCode.getException();
        }
    }
}
