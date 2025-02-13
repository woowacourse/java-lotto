package view;

import java.util.Scanner;

public class InputView {
    private static final String ASK_LOTTO_MONEY = "구입금액을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public String askForNormal() {
        System.out.println(ASK_LOTTO_MONEY);
        return getUserResponse();
    }

    private String getUserResponse() {
        return scanner.nextLine();
    }
}