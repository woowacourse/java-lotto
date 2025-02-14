package view;

import static error.ErrorMessage.BLANK_INPUT;
import static error.ErrorMessage.ONLY_POSITIVE_NUMBER;

import java.util.Scanner;

public class InputView {
    private static final String AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String REGEX = "\\d+";

    private final Scanner sc;

    private InputView() {
        sc = new Scanner(System.in);
    }

    public static InputView create() {
        return new InputView();
    }

    public int purchaseAmountInput() {
        printMessage(AMOUNT_INPUT_MESSAGE);
        String userInput = basicInput();
        validatePositiveNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private void validatePositiveNumber(String userInput) {
        if (!userInput.matches(REGEX)) {
            throw new IllegalArgumentException(ONLY_POSITIVE_NUMBER.getMessage());
        }
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
        String userInput = sc.nextLine();
        commonValidation(userInput);
        return userInput;
    }

    private void commonValidation(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT.getMessage());
        }
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }
}
