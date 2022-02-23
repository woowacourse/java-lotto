package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputValidation {

    private static final String ERROR_PRICE_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_NUM_OF_BALL = "로또 번호는 6개의 번호를 입력해줘야 합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";
    private static final String ERROR_NUMBER_OUT_RANGE = "로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.";
    private static final String ERROR_DUPLICATE_NUMBER = "로또 번호는 중복되면 안 됩니다.";

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
        checkDuplicateNumber(numbers);

        return numbers;
    }

    private static int checkNonInteger(String number, String message) {
        try {
            return Integer.parseInt(number);
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

    private static void checkDuplicateNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumber = numbers.stream()
                .collect(Collectors.toSet());

        if (uniqueNumber.size() != 6) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public static int validateBonusNumber(String bonus) {
        int bonusNumber = checkNonInteger(bonus, ERROR_BALL_NON_INTEGER);
        checkNumberRange(bonusNumber);

        return bonusNumber;
    }
}
