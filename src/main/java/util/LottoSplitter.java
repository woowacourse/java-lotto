package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoSplitter {
    private static final String LOTTO_NUMBER_SEPARATOR = ",";

    public static List<String> split(String input) {
        List<String> splittedInputs = Arrays.asList(input.split(LOTTO_NUMBER_SEPARATOR));

        return splittedInputs.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
