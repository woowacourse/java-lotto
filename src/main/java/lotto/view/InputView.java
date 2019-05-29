package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String getPurchaseAmount() {
        OutputView.start();
        return scanner.nextLine();
    }

    public static String getWinnerLotto() {
        OutputView.win();
        return scanner.nextLine();
    }

    public static String getBonusNumber() {
        OutputView.bonus();
        return scanner.nextLine();
    }
}
