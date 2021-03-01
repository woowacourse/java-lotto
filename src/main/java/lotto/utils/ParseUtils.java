package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.IllegalTypeException;

public class ParseUtils {

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalTypeException();
        }
    }

    public static List<Integer> parseIntegerList(String value, String regex) {
        try {
            return Arrays.stream(value.split(regex)).
                mapToInt(number -> Integer.parseInt(number))
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalTypeException();
        }
    }
}
