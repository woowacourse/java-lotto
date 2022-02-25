package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {

    private static final String PRICE_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";
    private static final String LENGTH_ERROR_MESSAGE = "[ERROR] 6개의 숫자가 입력되지 않았습니다.";
    private static final String NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 중복된 숫자가 존재합니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";
    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 숫자의 범위가 잘못되었습니다.";

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

    public static List<Integer> validateWinningNumbers(String winningNumbers) throws RuntimeException {
        String[] splitWinningNumbers = winningNumbers.split(",");

        validateLength(splitWinningNumbers);
        validateLottoNumbers(splitWinningNumbers);
        validateDuplicate(splitWinningNumbers);

        return Arrays.stream(splitWinningNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private static void validateLength(String[] winningNumbers) throws RuntimeException {
        if (winningNumbers.length != 6) {
            throw new RuntimeException(LENGTH_ERROR_MESSAGE);
        }
    }

    private static void validateLottoNumbers(String[] numbers) throws RuntimeException {
        for (String number : numbers) {
            validateLottoNumber(number);
        }
    }

    private static void validateLottoNumber(String number) throws RuntimeException {
        try {
            String trimNumber = number.trim();
            validateRange(Integer.parseInt(trimNumber));
        } catch (NumberFormatException e) {
            throw new RuntimeException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    private static void validateRange(int number) throws RuntimeException {
        if (number < 1 || number > 45) {
            throw new RuntimeException(RANGE_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(String[] winningNumbers) throws RuntimeException {
        long count = Arrays.stream(winningNumbers).distinct().count();
        if (count != winningNumbers.length) {
            throw new RuntimeException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public static int validateBonusNumber(String bonusNumber, List<Integer> winningNumbers) throws RuntimeException {
        validateLottoNumber(bonusNumber);
        if (winningNumbers.contains(Integer.parseInt(bonusNumber))) {
            throw new RuntimeException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
        return Integer.parseInt(bonusNumber);
    }
}
