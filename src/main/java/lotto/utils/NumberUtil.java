package lotto.utils;

import java.util.stream.Stream;

public class NumberUtil {
    public static int[] parsingNumber(String[] numbers) {
        return Stream.of(numbers)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
