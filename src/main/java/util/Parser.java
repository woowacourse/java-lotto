package util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static Integer convertStringToInteger(final String input) {
        return Integer.parseInt(input);
    }

    public static List<String> separateBySeparator(final String input, final String separator) {
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .toList();
    }
}
