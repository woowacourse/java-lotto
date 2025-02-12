package lotto;

import java.util.Scanner;

public class InputView {

    public int requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner in = new Scanner(System.in);
        int purchaseAmount = in.nextInt();
        validatePositive(purchaseAmount);
        return purchaseAmount;
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수일 수 없습니다.");
        }
    }
}
