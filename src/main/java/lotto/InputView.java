package lotto;

import java.util.Scanner;

public class InputView {

    public int requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
