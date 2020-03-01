package lotto.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static String[] parseString(String input) {
        return input.split(",");
    }

    public static long stringToLong(String input){
        return Long.parseLong(input);
    }

    public static int stringToInt(String input){
        return Integer.parseInt(input);
    }
}