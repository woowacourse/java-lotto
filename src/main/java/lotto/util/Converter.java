package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Converter {

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    public static List<Integer> convertToIntegerList(String input) {
        return Arrays.stream(input.split(","))
                .map(s -> Integer.parseInt(s.trim()))
                .toList();
    }

}
