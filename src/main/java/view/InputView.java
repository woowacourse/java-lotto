package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);


    public static int inputPurchaseMoney() {
        return Integer.parseInt(scanner.nextLine());
    }
}
