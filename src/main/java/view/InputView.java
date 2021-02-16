package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String RECEIVE_PRICE_MESSAGE = "구입금액을 입력해 주세요.";

    public static String receivePrice() {
        return receiveInput(RECEIVE_PRICE_MESSAGE);
    }

    private static String receiveInput(final String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }
}
