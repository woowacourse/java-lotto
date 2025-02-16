package lotto;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

    private TestUtil() {
    }

    public static List<Integer> parseToList(String argument) {
        return Arrays.stream(argument.split(":"))
            .map(Integer::parseInt)
            .toList();
    }
}
