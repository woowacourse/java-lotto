package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입급액을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public String inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextLine();
    }
}
