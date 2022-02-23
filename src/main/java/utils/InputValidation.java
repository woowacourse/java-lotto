package utils;

import java.util.Arrays;
import java.util.List;

public class InputValidation {

    private static final String ERROR_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_NUM_OF_BALL = "로또 번호는 6개의 번호를 입력해줘야 합니다.";

    public static int validatePrice(String inputPrice) {
        int price = checkNonInteger(inputPrice);
        checkUnderMinimumPrice(price);

        return price;
    }

    public static List<Integer> validateWinningNumber(String inputNumbers) {
        List<String> splitedNumbers = Arrays.asList(inputNumbers.split(", "));
        checkNumOfNumbers(splitedNumbers);

        return null;
    }


    private static int checkNonInteger(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NON_INTEGER);
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

}
