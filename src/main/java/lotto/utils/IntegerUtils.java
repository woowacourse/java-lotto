package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerUtils {

    private static final String DELIMITER = ",";

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력한 값이 숫자가 아닙니다.");
        }
    }

    public static List<Integer> parseAll(String input) {
        return Arrays.stream(input.split(DELIMITER))
            .map(string -> IntegerUtils.parse(string.trim()))
            .collect(Collectors.toList());
    }
}
