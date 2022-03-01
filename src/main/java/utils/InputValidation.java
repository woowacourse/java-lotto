package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputValidation {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_PRICE = 1000;
    private static final String LOTTO_NUMBER_DELIMITER = ", |,";
    private static final String ERROR_PRICE_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_NUM_OF_BALLS = "로또 번호는 6개의 번호를 입력해줘야 합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";

    public static int validatePrice(final String inputPrice) {
        final int price = checkNonInteger(inputPrice, ERROR_PRICE_NON_INTEGER);
        checkUnderMinimumPrice(price);

        return price;
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

    public static Set<Integer> validateWinningNumber(final String inputNumbers) {
        final List<String> splitNumbers = Arrays.asList(inputNumbers.split(LOTTO_NUMBER_DELIMITER));
        checkNumOfBalls(splitNumbers);

        return checkNonIntegers(splitNumbers);
    }

    private static void checkNumOfBalls(List<String> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NUM_OF_BALLS);
        }
    }

    private static Set<Integer> checkNonIntegers(List<String> numbers) {
        return numbers.stream()
                .map(number -> checkNonInteger(number, ERROR_BALL_NON_INTEGER))
                .collect(Collectors.toSet());
    }

    public static int validateBonusNumber(final String bonus) {
        return checkNonInteger(bonus, ERROR_BALL_NON_INTEGER);
    }

}
