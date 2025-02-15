package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Converter {

    private Converter() {

    }

    public static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public static List<Integer> convertToIntegerList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(s -> Integer.parseInt(s.trim()))
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("콤마로 구분된 숫자만 입력 가능합니다.");
        }
    }

}
