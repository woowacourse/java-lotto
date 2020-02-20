package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitLottoNumbers {
    private static final String COMMA = ",";

    public static List<Integer> splitLottoNumbers(String input) {
        return Arrays.stream(input.split(COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
