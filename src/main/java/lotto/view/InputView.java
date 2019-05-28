package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        OutputView.start();
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getWinnerLotto() {
        OutputView.win();
        return scanner.nextLine();
    }
}
