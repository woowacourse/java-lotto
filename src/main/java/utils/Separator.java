package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Separator {

    public static List<Integer> splitStringToListInt(final String input, final String regex) {
        return Arrays.stream(input.split(regex))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
