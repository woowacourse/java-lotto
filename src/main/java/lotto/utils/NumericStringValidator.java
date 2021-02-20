package lotto.utils;

import java.util.regex.Pattern;

public class NumericStringValidator {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public static boolean isValid(String string) {
        return NUMERIC_PATTERN.matcher(string).matches();
    }
}
