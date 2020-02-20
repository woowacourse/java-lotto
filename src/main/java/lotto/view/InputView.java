package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String COMMA = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }

    public static String[] getWinningNumbers() {
        return getInput().split(COMMA);
    }

}
