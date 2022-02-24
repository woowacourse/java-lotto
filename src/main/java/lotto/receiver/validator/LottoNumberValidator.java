package lotto.receiver.validator;

import java.util.regex.Pattern;
import lotto.exception.LottoNumberException;

public class LottoNumberValidator {

    private static final String REGEX_FOR_NATURAL_NUMBER = "^[1-9][0-9]*$";
    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    public static void validate(String input) {
        checkNaturalNumber(input);
        checkRange(input);
    }

    private static void checkNaturalNumber(String input) {
        if (!isNaturalNumber(input)) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private static boolean isNaturalNumber(String input) {
        return Pattern.compile(REGEX_FOR_NATURAL_NUMBER).matcher(input).find();
    }

    private static void checkRange(String input) {
        if (!isCorrectRange(Integer.parseInt(input))) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectRange(int input) {
        return input >= MINIMUM_RANGE && input <= MAXIMUM_RANGE;
    }
}
