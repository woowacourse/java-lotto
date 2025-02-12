package view;

import java.util.Scanner;

public class InputView {

    private static final String PURCHASE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";

    public String getPurchaseInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PURCHASE_INPUT_MESSAGE);
        return scanner.next();
    }
}
