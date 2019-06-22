package lotto.utils;

import java.util.stream.Stream;

public class NumberUtil {
    public static int[] parsing(String[] numbers) {
        return Stream.of(numbers)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
