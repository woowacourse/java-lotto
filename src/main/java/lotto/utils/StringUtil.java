package lotto.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() {
    }

    public static String[] getSplit(String text) {
        return text.split(", ");
    }

    public static List<Integer> toIntegers(String[] strings) {
        List<Integer> numbers = new ArrayList<>();
        try {
            numbers = Arrays.stream(strings)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
        }
        return numbers;
    }
}
