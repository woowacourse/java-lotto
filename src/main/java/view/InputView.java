package view;

import java.util.Scanner;

public class InputView {

    private static final String AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private final Scanner sc;

    private InputView() {
        sc = new Scanner(System.in);
    }

    public static InputView create() {
        return new InputView();
    }

    public void amountInputMessage() {
        printMessage(AMOUNT_INPUT_MESSAGE);
    }

    public String getAmountInput() {
        return sc.next();
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }
}
