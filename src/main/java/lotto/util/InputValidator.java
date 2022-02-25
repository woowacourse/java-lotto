package lotto.util;

import java.util.regex.Pattern;
import lotto.model.Money;

public class InputValidator {

    private static final String MONEY_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";

    private static final String NUMBER_REGEX = "\\d+";

    public static Money validateMoney(String money) {
        if (!Pattern.matches(NUMBER_REGEX, money)) {
            throw new RuntimeException(MONEY_ERROR_MESSAGE);
        }
        return new Money(Integer.parseInt(money));
    }

    public static String[] validateLottoNumbers(String numbers) throws RuntimeException {
        String[] splitNumbers = numbers.split(",");
        for (String number : splitNumbers) {
            validateNumber(number);
        }
        return splitNumbers;
    }

    public static int validateNumber(String number) throws RuntimeException {
        try {
            String trimNumber = number.trim();
            return Integer.parseInt(trimNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }
}
