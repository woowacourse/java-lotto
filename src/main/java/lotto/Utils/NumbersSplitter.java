package lotto.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersSplitter {
    private static final String SPLIT_REGEX = ",";

    private NumbersSplitter() {
        throw new AssertionError();
    }

    public static List<Integer> splitNumbers(String numbers) {
        return Arrays.stream(numbers.split(SPLIT_REGEX))
                .map(number -> Integer.parseInt(number.trim()))
                .collect(Collectors.toList());
    }
}
