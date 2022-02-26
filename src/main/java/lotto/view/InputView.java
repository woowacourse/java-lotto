package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static String inputPurchaseAmount() {
        return input();
    }

    public static List<String> inputWinningNumber() {
        return Arrays.asList(input().trim().split(DELIMITER));
    }

    public static String inputBonusBall() {
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
