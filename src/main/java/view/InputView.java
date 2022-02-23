package view;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_MESSAGE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String ERROR_MESSAGE_TYPE_OF_MONEY = "금액은 숫자가 아닐 수 없습니다.";
    private static final String ERROR_MESSAGE_RANGE_OF_MONEY = "금액은 0이하일 수 없습니다.";

    private static final Scanner scanner = new Scanner(System.in);

    private static String getInput() {
        return scanner.nextLine();
    }

    public static int getPurchaseMoney() {
        System.out.println(REQUEST_MESSAGE_INPUT_PURCHASE_MONEY);
        String inputMoney = getInput();
        validatePurchaseMoney(inputMoney);

        return Integer.parseInt(inputMoney);
    }

    private static void validatePurchaseMoney(String value) {
        validateNumber(value);
        validateRange(value);
    }

    private static void validateNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TYPE_OF_MONEY);
        }
    }

    private static void validateRange(String value) {
        if (Integer.parseInt(value) <= 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RANGE_OF_MONEY);
        }
    }
}
