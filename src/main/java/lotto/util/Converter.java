package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Converter {

    private Converter() {

    }

    public static List<Integer> convertToIntegerList(String input) {
        return Arrays.stream(input.split(","))
                .map(s -> Integer.parseInt(s.trim()))
                .toList();
    }

}
