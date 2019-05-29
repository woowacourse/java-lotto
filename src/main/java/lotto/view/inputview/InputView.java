package lotto.view.inputview;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner;

    public static String inputPrice() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
