package lotto.View;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String input() {
        return scanner.nextLine().trim();
    }

    public static String inputMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return input();
    }
}
