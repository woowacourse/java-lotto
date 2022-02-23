package view;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_MESSAGE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";

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
    }

    private static void validateNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
