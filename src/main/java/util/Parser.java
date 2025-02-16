package util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static Integer convertStringToInteger(String input) {
        return Integer.parseInt(input);
    }

    public static List<String> separateBySeparator(String input, String separator) {
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .toList();
    }
}
