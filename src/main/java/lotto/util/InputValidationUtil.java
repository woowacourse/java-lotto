package lotto.util;

import lotto.Exception.NotPositiveNumberException;

public class InputValidationUtil {

    private static final int ZERO = 0;

    private InputValidationUtil() {
    }

    public static int returnNumberWithNumberCheck(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다. 재입력 해주세요.");
        }
    }

    public static void isPositiveNumber(long inputValue) {
        if(inputValue < ZERO){
            throw new NotPositiveNumberException("음수입니다. 재입력 해주세요.");
        }
    }
}
