package lotto.utils;

import java.util.regex.Pattern;

public class StringChecker {
    private static final Pattern INT_NUMBER = Pattern.compile("\\d+");

    public static boolean isNumber(String value) {
        return INT_NUMBER.matcher(value).matches();
    }

}
