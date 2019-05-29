package lotto.utils;

public class InputUtils {
    public static int toInts(String input) {
        return Integer.parseInt(input);
    }

    public static String[] split(String input) {
        return input.trim().split(",");
    }

    public static boolean isValidRange(Integer number, Integer limitMinimumNumber, Integer limitMaximumNumber) {
        return limitMinimumNumber <= number && number <= limitMaximumNumber;
    }
}
