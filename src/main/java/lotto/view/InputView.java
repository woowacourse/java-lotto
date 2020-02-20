package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String COMMA = ",";
    private static final Scanner scanner = new Scanner("14200\n1, 2, 3, 4, 5, 6\n7\n");

    public static String getInput() {
        return scanner.nextLine();
    }

    public static String[] getWinningNumbers() {
        return getInput().split(COMMA);
    }

}
