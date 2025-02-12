package view;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);

    public static String inputBuyLottoMoney() {
        OutputView.printBuyLottoMoney();
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
