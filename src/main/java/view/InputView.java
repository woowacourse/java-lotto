package view;

public class InputView {

    private static final String AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";

    private InputView() {}

    public static InputView create() {
        return new InputView();
    }

    public void amountInputMessage() {
        printMessage(AMOUNT_INPUT_MESSAGE);
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }
}
