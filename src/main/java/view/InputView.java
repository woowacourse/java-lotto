package view;

import java.util.Scanner;

public class InputView {

    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";

    public String askPurchaseAmount() {
        Scanner sc = new Scanner(System.in);

        System.out.println(PURCHASE_AMOUNT_PROMPT);
        String purchaseAmount = sc.nextLine();
        sc.close();

        return purchaseAmount;
    }
}
