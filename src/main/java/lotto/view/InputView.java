package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    public static String inputMoney(Scanner scanner) {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextLine();
    }
}
