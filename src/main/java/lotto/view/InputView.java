package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PURCHASE_MONEY = "구매금액을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(INPUT_PURCHASE_MONEY);
        return Integer.parseInt(scanner.nextLine());
    }
}
