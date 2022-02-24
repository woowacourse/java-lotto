package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String PRICE_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";
    private static final String LENGTH_ERROR_MESSAGE = "[ERROR] 6개의 숫자가 입력되지 않았습니다.";
    private static final String NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 중복된 숫자가 존재합니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";
    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 숫자의 범위가 잘못되었습니다.";

    private static final String REGEX = "\\d+";

    public static void validatePrice(String price) {
        if (!Pattern.matches(REGEX, price)) {
            throw new RuntimeException(PRICE_ERROR_MESSAGE);
        }
    }

    public static void validateWinningNumbers(String winningNumbers) {
        String[] splitWinningNumbers = winningNumbers.split(",");

        validateLength(splitWinningNumbers);
        for (String number : splitWinningNumbers) {
            validateNumber(number);
        }
        validateDuplicate(splitWinningNumbers);
    }

    private static void validateLength(String[] winningNumbers) {
        if (winningNumbers.length != 6) {
            throw new RuntimeException(LENGTH_ERROR_MESSAGE);
        }
    }

    private static void validateNumber(String number) {
        try {
            String trimNumber = number.trim();
            validateRange(Integer.parseInt(trimNumber));
        } catch (NumberFormatException e) {
            throw new RuntimeException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    private static void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new RuntimeException(RANGE_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(String[] winningNumbers) {
        long count = Arrays.stream(winningNumbers).distinct().count();
        if (count != winningNumbers.length) {
            throw new RuntimeException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public static void validateBonusNumber(String bonusNumber, List<Integer> winningNumbers) {
        validateNumber(bonusNumber);
        if (winningNumbers.contains(Integer.parseInt(bonusNumber))) {
            throw new RuntimeException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }
}
