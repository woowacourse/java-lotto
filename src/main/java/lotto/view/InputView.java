package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner("14000\n");

    public static String getInput() {
        return scanner.nextLine();
    }
}
