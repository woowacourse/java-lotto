package lotto.utils;

public class ValidationUtils {
    private static final int ZERO = 0;
    private static final String POSSIBLE_ONLY_INTEGER = "정수만 입력 가능합니다. 재입력 해주세요.";
    private static final String NEGATIVE_NUMBER = "음수입니다. 재입력 해주세요";
    private static final String OVERFLOW = "OverFlow 발생";
    private static final String UNDERFLOW = "UnderFlow 발생";


    public static void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException(POSSIBLE_ONLY_INTEGER);
        }
    }

    public static void validateIntegerNumberFormat(String[] input) {
        for (String balls : input) {
            validateIntegerNumberFormat(balls);
        }
    }

    public static void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER);
        }
    }

    public static void validatePositiveNumber(String[] input) {
        for (String balls : input) {
            validatePositiveNumber(balls);
        }
    }


    public static void validateUnderFlowOrOverFlow(double totalMoney) {
        if (Double.isInfinite(totalMoney)) {
            throw new IllegalArgumentException(OVERFLOW);
        }
        if (Double.isNaN(totalMoney)) {
            throw new IllegalArgumentException(UNDERFLOW);
        }
    }
}
