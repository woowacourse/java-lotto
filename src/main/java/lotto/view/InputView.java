package lotto.view;

import java.util.Scanner;

import lotto.utils.InputParser;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        OutputView.start();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static int getManualCount() {
        OutputView.manualCount();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static String getWinnerLotto() {
        OutputView.win();
        return scanner.nextLine();
    }

    public static int getBonusNumber() {
        OutputView.bonus();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static String getManualLotto() {
        return scanner.nextLine();
    }
}
