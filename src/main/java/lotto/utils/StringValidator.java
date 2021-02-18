package lotto.utils;

import java.util.regex.Pattern;

public class StringValidator {

    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");

    public static void validateIsDigit(String input) {
        if (!DIGIT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
