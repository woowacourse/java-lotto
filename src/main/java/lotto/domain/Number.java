package lotto.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number {
    private static final Pattern INPUTPATTENR = Pattern.compile("^[0-9]{1,}$");
    private static final String ERROR_MESSAGE = "잘못된 입력입니다. 다시 입력해주세요.";
    private static final int MIN = 1;
    private static final int MAX = 45;
    private final int number;

    public Number(String number) {
        if (validInputString(number) || validOverBound(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        this.number = Integer.parseInt(number);
    }

    private boolean validOverBound(String input) {
        int number = Integer.parseInt(input);
        return number < MIN || number > MAX;
    }

    private boolean validInputString(String input) {
        Matcher matcher = INPUTPATTENR.matcher(input);
        return !matcher.find();
    }
}
