package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Separator {
    private static final String REGEX = ", ";

    public static List<Integer> separateNumbers(String numbersText) {
        return Arrays.stream(numbersText.split(REGEX))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}