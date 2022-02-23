package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String getInput() {
        return SCANNER.nextLine();
    }

    public static String getMoney() {
        OutputView.printMoneyInstruction();
        return getInput();
    }

    public static String getWinNumbers() {
        OutputView.printWinNumbersInstruction();
        return getInput();
    }

    public static String getBonusNumber() {
        OutputView.printBonusInstruction();
        return getInput();
    }
}
