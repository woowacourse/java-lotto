package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해주세요.");
            String purchasedAmount = SCANNER.nextLine().trim();
            return Integer.parseInt(purchasedAmount);
        } catch (NumberFormatException e) {
            System.out.println("정수만 입력할 수 있습니다.");
            return inputPurchaseAmount();
        }
    }
}
