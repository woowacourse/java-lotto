package lotto.domain;

import java.util.regex.Pattern;
import lotto.exception.MoneyException;

public class Money {

    private static final String REGEX_FOR_NATURAL_NUMBER = "^[1-9][0-9]*$";
    private static final int UNIT_SIZE = 1000;

    private final int money;

    public Money(String input) {
        checkNaturalNumber(input);
        checkUnit(input);
        this.money = Integer.parseInt(input);
    }

    private void checkNaturalNumber(String input) {
        if (!isNaturalNumber(input)) {
            throw new MoneyException(MoneyException.MONEY_ONLY_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean isNaturalNumber(String input) {
        return Pattern.compile(REGEX_FOR_NATURAL_NUMBER).matcher(input).find();
    }

    private void checkUnit(String input) {
        if (!isCorrectUnit(input)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private boolean isCorrectUnit(String input) {
        return Integer.parseInt(input) % UNIT_SIZE == 0;
    }

    public int getMoney() {
        return money;
    }
}
