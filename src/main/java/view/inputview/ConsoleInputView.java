package view.inputview;

import java.util.Scanner;

public abstract class ConsoleInputView<T> implements InputView<T> {
    private static final String IS_NULL_OR_BLANK_ERROR_MESSAGE = "입력값이 확인되지 않습니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public String inputLine() {
        String userInput = scanner.nextLine();
        inputNullOrBlankCheck(userInput);
        return userInput;
    }

    private void inputNullOrBlankCheck(String inputText) {
        if (isNullOrBlank(inputText)) {
            throw new IllegalArgumentException(IS_NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }

    private boolean isNullOrBlank(String inputText) {
        return inputText == null || inputText.isBlank();
    }
}
