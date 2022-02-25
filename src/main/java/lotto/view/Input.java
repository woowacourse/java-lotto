package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static String purchaseAmount() {
        return input();
    }

    public static List<String> winNumber() {
        return Arrays.asList(input().trim().split(DELIMITER));
    }

    public static String bonusBall() {
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
