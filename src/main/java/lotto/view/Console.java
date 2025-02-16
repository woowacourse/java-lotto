package lotto.view;

import java.util.Scanner;

public class Console {

    private static Scanner scanner;

    private Console() {
    }

    public static String readLine() {
        return getScanner().nextLine();
    }

    private static Scanner getScanner() {
        if (scanner != null) {
            return scanner;
        }
        scanner = new Scanner(System.in);
        return scanner;
    }

    public static void close() {
        if (scanner == null) {
            return;
        }
        scanner.close();
        scanner = null;
    }
}
