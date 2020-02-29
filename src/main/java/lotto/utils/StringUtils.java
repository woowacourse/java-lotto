package lotto.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static String[] parseString(String input) {
        return input.split(",");
    }
}