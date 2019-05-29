package lottogame.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersParser{
    public static List<Integer> parse(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
