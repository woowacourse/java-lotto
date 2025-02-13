package view;

import java.util.Scanner;

public class InputView {

    private static final String AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner sc;

    private InputView() {
        sc = new Scanner(System.in);
    }

    public static InputView create() {
        return new InputView();
    }

    public int purchaseAmountInput() {
        printMessage(AMOUNT_INPUT_MESSAGE);
        return Integer.parseInt(basicInput());
    }

    public String winningNumbersInput() {
        printMessage(WINNING_NUMBERS_INPUT_MESSAGE);
        return basicInput();
    }

    public int bonusNumberInput() {
        printMessage(BONUS_NUMBER_INPUT_MESSAGE);
        return Integer.parseInt(basicInput());
    }

    private String basicInput() {
        return sc.next();
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }
}
