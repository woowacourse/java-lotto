package utils;

import java.util.Arrays;
import java.util.List;

public class InputValidation {

    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String ERROR_NON_INTEGER = "정수 입력만 가능합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";

    public static String validateIsNum(final String inputPrice) {
        checkIsInteger(inputPrice, ERROR_NON_INTEGER);
        return inputPrice;
    }

    public static List<String> validateIsNumsListString(final String inputNumbers) {
        final List<String> splitNumbers = Arrays.asList(inputNumbers.split(LOTTO_NUMBER_DELIMITER));
        for (String number : splitNumbers) {
            checkIsInteger(number, ERROR_BALL_NON_INTEGER);
        }
        return splitNumbers;
    }

    public static String validateBallInput(final String ballInput) {
        checkIsInteger(ballInput, ERROR_BALL_NON_INTEGER);
        return ballInput;
    }

    private static void checkIsInteger(final String number, final String message) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

}
