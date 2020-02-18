package lotto.view;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";

    public static int inputAmount() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextInt();
    }
}
