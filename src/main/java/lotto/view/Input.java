package lotto.view;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String purchaseAmount() {
        return input();
    }

    public static String lastWeeksWinningNumbers() {
        return input();
    }

    public static String bonusBall() {
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
