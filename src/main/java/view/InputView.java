package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMA = ",";

    public static String inputPurchaseAmount() {
        OutputView.printInputPurchaseAmountMessage();
        return scanner.nextLine();
    }

    public static String inputManualCount() {
        OutputView.printInputManualCountMessage();
        return scanner.nextLine();
    }

    public static String[] inputWinningNumbers() {
        OutputView.printInputWinningNumbersMessage();
        return scanner.nextLine()
                    .split(COMMA);
    }

    public static String inputBonusNumber() {
        OutputView.printInputBonusNumberMessage();
        return scanner.nextLine();
    }
}
