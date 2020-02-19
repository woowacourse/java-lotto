package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static String input() {
        String input = sc.nextLine();
        getWinNumbers(input);
        return input;
    }

    public static void getWinNumbers(String input) {
        if (input == null || input.equals("")) {
            throw new NullPointerException("null 혹은 빈문자열을 입력할 수 없습니다.");
        }
    }
}
