package lotto.util;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String PRICE_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";

    private static final String NUMBER_REGEX = "\\d+";

    private static final int PRICE_PER_LOTTO = 1000;

    public static int validatePrice(String price) throws RuntimeException {
        if (!Pattern.matches(NUMBER_REGEX, price) || isLessThanLottoPrice(Integer.parseInt(price))) {
            throw new RuntimeException(PRICE_ERROR_MESSAGE);
        }
        return Integer.parseInt(price) / PRICE_PER_LOTTO;
    }

    private static boolean isLessThanLottoPrice(int price) {
        return price < PRICE_PER_LOTTO;
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
