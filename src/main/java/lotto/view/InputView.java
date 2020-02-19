package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() { /* prevent creating InputView instance */ }

    public static String requestPurchasePriceInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }
}
