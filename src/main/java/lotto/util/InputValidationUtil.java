package lotto.util;

import lotto.Exception.NotPositiveNumberException;

public class InputValidationUtil {
    private static final int ZERO = 0;
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "숫자가 아닙니다. 재입력 해주세요.";
    public static final String NOT_POSITIVE_NUMBER_ERROR_MESSAGE = "음수입니다. 재입력 해주세요.";

    private InputValidationUtil() {
    }

    public static int returnNumberWithNumberCheck(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public static void isPositiveNumber(long inputValue) {
        if (inputValue < ZERO) {
            throw new NotPositiveNumberException(NOT_POSITIVE_NUMBER_ERROR_MESSAGE);
        }
    }
}