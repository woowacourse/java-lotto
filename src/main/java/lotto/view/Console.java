package lotto.view;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    private Console() {
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
