package lotto.utils;

import lotto.view.errors.InvalidInputException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String DELIMITER = ", ";

    public static List<Integer> parseWithDelimiter(String input) {
        if (!input.contains(DELIMITER)) {
            throw new InvalidInputException(String.format("구분자(%s)를 가져야 합니다.", DELIMITER));
        }
        return Arrays.stream(input.split(DELIMITER))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
    }

    public static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
