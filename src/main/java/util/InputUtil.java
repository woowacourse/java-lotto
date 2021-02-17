package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {

    }

    public static String nextLine() {
        return SCANNER.nextLine();
    }

    public static int nextInt() {
        String inputValue = SCANNER.nextLine();
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(String.format("자연수만 입력 가능합니다. | 현재 입력 값 : %s ", inputValue));
        }
    }
}
