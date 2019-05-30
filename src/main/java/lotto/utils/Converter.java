package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static List<Integer> convertNumbers(String input) {
        List<String> inputs = splitInputs(input);
        return inputs.stream().map(String::trim).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

    private static List<String> splitInputs(String input) {
        return Arrays.asList(input.split(","));
    }
}
