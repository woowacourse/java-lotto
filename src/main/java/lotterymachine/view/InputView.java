package lotterymachine.view;

import java.util.Scanner;

import static lotterymachine.view.ErrorMessage.IS_NOT_NUMBER_ERROR_MESSAGE;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getAmountInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(IS_NOT_NUMBER_ERROR_MESSAGE.getMessage());
            return getAmountInput();
        }
    }
}
