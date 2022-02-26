package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidation {

    private static final int LOTTO_PRICE = 1000;
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String ERROR_PRICE_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";

    public static int validatePrice(final String inputPrice) {
        final int price = checkNonInteger(inputPrice, ERROR_PRICE_NON_INTEGER);
        checkUnderMinimumPrice(price);

        return price;
    }

    public static List<Integer> validateWinningNumber(final String inputNumbers) {
        final List<String> splitNumbers = Arrays.asList(inputNumbers.split(LOTTO_NUMBER_DELIMITER));
        final List<Integer> numbers = checkNonIntegers(splitNumbers);
        return numbers;
    }

    private static int checkNonInteger(final String number, final String message) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void checkUnderMinimumPrice(final int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INTEGER);
        }
    }

    private static List<Integer> checkNonIntegers(final List<String> numbers) {
        return numbers.stream()
                .map(number -> checkNonInteger(number, ERROR_BALL_NON_INTEGER))
                .collect(Collectors.toList());
    }

    public static int validateBonusNumber(final String bonus) {
        final int bonusNumber = checkNonInteger(bonus, ERROR_BALL_NON_INTEGER);
        return bonusNumber;
    }

}
