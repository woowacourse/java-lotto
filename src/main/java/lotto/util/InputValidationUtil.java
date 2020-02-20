package lotto.util;

public class InputValidationUtil {

    private InputValidationUtil() {
    }

    public static int returnNumberWithNumberCheck(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    public static void isPositiveNumber(long inputValue) {
        if(inputValue < 0){
            throw new IllegalArgumentException("음수입니다.");
        }
    }
}
