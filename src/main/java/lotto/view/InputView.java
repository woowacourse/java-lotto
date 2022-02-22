package lotto.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ERROR_NO_INPUT_MESSAGE = "입력이 없습니다.";
    private static final String ERROR_INPUT_EMPTY_MESSAGE = "공백 또는 빈 문자열이 입력하면 안됩니다.";
    private static final String ERROR_MONEY_NOT_NUMBER_MESSAGE = "금액은 숫자 이외의 문자가 입력하면 안됩니다.";

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestPurchaseMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = nextLine();
        validateEmpty(input);
        return toInt(input);
    }

    private static String nextLine() {
        String input;
        try {
            input = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(ERROR_NO_INPUT_MESSAGE);
        }
        return input;
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INPUT_EMPTY_MESSAGE);
        }
    }

    private static int toInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_MONEY_NOT_NUMBER_MESSAGE);
        }
    }
}
