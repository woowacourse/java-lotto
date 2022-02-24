package lotto.utils;

import java.util.List;

public enum Delimiter {

    COMMA(","),
    SPACE(" ");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public static String[] splitWithComma(final String targetString) {
        return targetString.split(COMMA.unit);
    }

    public static String appendSpaceBehind(final String targetString) {
        return targetString + SPACE.unit;
    }

    public static String joinWithComma(final List<String> strings) {
        return String.join(COMMA.unit + SPACE.unit, strings);
    }

}
