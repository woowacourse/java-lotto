package lotto.util.regex;

import java.util.regex.Pattern;

public class NumberRegex {
    private NumberRegex() {
        throw new IllegalStateException();
    }

    public static final Pattern NATURAL_NUMBER_REGEX = Pattern.compile("^[1-9][0-9]*$");
}
