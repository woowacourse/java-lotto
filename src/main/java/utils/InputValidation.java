package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidation {

    private static final String ERROR_PRICE_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_NUM_OF_BALL = "로또 번호는 6개의 번호를 입력해줘야 합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";
    private static final String ERROR_NUMBER_OUT_RANGE = "로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.";

    public static int validatePrice(String inputPrice) {
        int price = checkNonInteger(inputPrice, ERROR_PRICE_NON_INTEGER);
        checkUnderMinimumPrice(price);

        return price;
    }

    public static List<Integer> validateWinningNumber(String inputNumbers) {
        List<String> splitedNumbers = Arrays.asList(inputNumbers.split(", "));
        checkNumOfNumbers(splitedNumbers);

        List<Integer> numbers = checkNonIntegers(splitedNumbers);
        checkNumbersRange(numbers);

        return numbers;
    }

    private static int checkNonInteger(String price, String message) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void checkUnderMinimumPrice(int price) {
        if (price < 1000) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INTEGER);
        }
    }

    private static void checkNumOfNumbers(List<String> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_NUM_OF_BALL);
        }
    }

    private static List<Integer> checkNonIntegers(List<String> numbers) {
        return numbers.stream()
                .map(number -> checkNonInteger(number, ERROR_BALL_NON_INTEGER))
                .collect(Collectors.toList());
    }

    private static void checkNumbersRange(List<Integer> numbers) {
        numbers.stream()
                .forEach(number -> checkNumberRange(number));
    }

    private static void checkNumberRange(int number) {
        if (!(number >= 1 && number <= 45)) {
            throw new IllegalArgumentException(ERROR_NUMBER_OUT_RANGE);
        }
    }

}
