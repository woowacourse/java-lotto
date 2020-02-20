package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String STANDARD = ", ";

    public static List<Integer> parseWithStandard(String input) {
        return Arrays.stream(input.split(STANDARD))
            .mapToInt(number -> Integer.parseInt(number))
            .boxed()
            .collect(Collectors.toList());
    }

    public static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
