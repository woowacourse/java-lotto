package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtility {
    public static List<Integer> parseIntList(String s, String delimiter) {
        return Arrays.stream(s.split(delimiter))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
