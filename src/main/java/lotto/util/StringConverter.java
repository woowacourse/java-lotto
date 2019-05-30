package lotto.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringConverter {
    private static final String DELIMITER = ",";

    public static List<Integer> toNumbers(String originText) {
        List<Integer> numbers = parse(originText).stream()
                .map(x -> Integer.parseInt(x.trim()))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(numbers);
    }

    private static List<String> parse(String originText) {
        return Arrays.asList(originText.split(DELIMITER));
    }
}
