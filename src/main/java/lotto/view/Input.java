package lotto.view;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        return input();
    }

    public static String inputWinNumber() {
        return input();
    }

    public static String inputBonusBall() {
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
