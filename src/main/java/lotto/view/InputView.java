package lotto.view;

import java.util.Scanner;

public class InputView {
    public static final String COMMA = ",";
    private static final Scanner scanner = new Scanner("14000\n");

    public static String getInput() {
        return scanner.nextLine();
    }

    public static String[] getWinningNumbers() {
        return getInput().split(COMMA);
    }

}
