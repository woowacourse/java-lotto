package lotto.utils;

public class InputValidateUtils {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

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

    public static void inputOutOfRange(String number, String message) {
        if (ConverterUtils.convertStringToInt(number) < LOTTO_MIN_NUMBER ||
                ConverterUtils.convertStringToInt(number) > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(message);
        }
    }
}
