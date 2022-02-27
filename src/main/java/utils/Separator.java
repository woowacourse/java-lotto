package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Separator {

    private static final String REGEX = ", ";

    public static List<Integer> splitStringToListInt(String input) {
        return Arrays.stream(input.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
    }
}
