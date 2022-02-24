package lotto.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Number;

public class StringUtil {

    private static final String SPLIT_DELIMITER = ",";

    private StringUtil() {
    }

    public static List<Integer> toIntegers(String text) {
        List<String> strings = getSplit(text);
        List<Integer> numbers = new ArrayList<>();
        try {
            numbers = strings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
        }
        return numbers;
    }

    private static List<String> getSplit(String text) {
        return Arrays.stream(text.split(SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Number toNumber(String input) {
        try {
            return new Number(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
