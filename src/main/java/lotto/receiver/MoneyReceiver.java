package lotto.receiver;

import java.util.regex.Pattern;
import lotto.exception.MoneyException;

public class MoneyReceiver {

    private static final String REGEX_FOR_NATURAL_NUMBER = "^[1-9][0-9]*$";
    private static final int UNIT_SIZE = 1000;

    public static int receive(String input) {
        checkNaturalNumber(input);
        checkUnit(input);
        return Integer.parseInt(input);
    }

    private static void checkNaturalNumber(String input) {
        if (!isNaturalNumber(input)) {
            throw new MoneyException(MoneyException.MONEY_ONLY_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private static boolean isNaturalNumber(String input) {
        return Pattern.compile(REGEX_FOR_NATURAL_NUMBER).matcher(input).find();
    }

    private static void checkUnit(String input) {
        if (!isCorrectUnit(input)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectUnit(String input) {
        return Integer.parseInt(input) % UNIT_SIZE == 0;
    }
}
