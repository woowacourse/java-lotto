package lotto.util;

public class InputValidationUtil {

    private static final int ZERO = 0;
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자가 아닙니다. 재입력 해주세요.";
    private static final String NOT_POSITIVE_NUMBER_EXCEPTION = "음수입니다. 재입력 해주세요.";

    private InputValidationUtil() {
    }

    public static int returnNumberWithNumberCheck(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_EXCEPTION);
        }
    }

    public static void isPositiveNumber(long inputValue) {
        if (inputValue < ZERO) {
            throw new IllegalArgumentException(NOT_POSITIVE_NUMBER_EXCEPTION);
        }
    }
}
