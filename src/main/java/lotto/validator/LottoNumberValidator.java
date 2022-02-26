package lotto.validator;

import lotto.exception.LottoNumberException;

public class LottoNumberValidator {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    public static void validate(int input) {
        checkRange(input);
    }

    private static void checkRange(int input) {
        if (!isCorrectRange(input)) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectRange(int input) {
        return input >= MINIMUM_RANGE && input <= MAXIMUM_RANGE;
    }
}
