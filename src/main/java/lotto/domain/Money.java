package lotto.domain;

import java.util.regex.Pattern;
import lotto.exception.MoneyOnlyNaturalNumberException;

public class Money {

    private static final String REGEX_FOR_NATURAL_NUMBER = "^[1-9][0-9]*$";

    private final long money;

    public Money(String input) {
        checkNaturalNumber(input);
        this.money = Integer.parseInt(input);
    }

    void checkNaturalNumber(String input) {
        if (!isNaturalNumber(input)) {
            throw new MoneyOnlyNaturalNumberException();
        }
    }

    boolean isNaturalNumber(String input) {
        return Pattern.compile(REGEX_FOR_NATURAL_NUMBER).matcher(input).find();
    }
}
