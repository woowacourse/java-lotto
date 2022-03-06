package lotto.utils;

import static lotto.model.lotto.LottoNumbers.MAX;
import static lotto.model.lotto.LottoNumbers.MIN;

public class InputValidateUtils {
    private static final String REGEX_NUMBER = "[0-9]+";

    private InputValidateUtils() {
    }

    public static void inputBlank(String number, String message) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void inputNumber(String number, String message) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void inputOutOfRange(int number, String message) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(message);
        }
    }
}
