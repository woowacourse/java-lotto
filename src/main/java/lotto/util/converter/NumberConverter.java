package lotto.util.converter;

import java.util.regex.Pattern;

public class NumberConverter {
    private static final Pattern INTEGER_REGEX = Pattern.compile("^[+-]?\\d*(\\d*)$");
    private static final String NOT_INTEGER_EXCEPTION_MESSAGE = "정수를 입력하셔야 합니다.";

    private NumberConverter() {
        throw new AssertionError();
    }

    public static int convertStringToInt(final String value) {
        if (!INTEGER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(NOT_INTEGER_EXCEPTION_MESSAGE);
        }
        return Integer.parseInt(value);
    }
}
