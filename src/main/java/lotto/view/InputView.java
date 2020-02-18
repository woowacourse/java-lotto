package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);
    public static int inputPayment() {
        try {
            int payment = sc.nextInt();
            return payment;
        } catch (Exception e) {
            throw new NumberFormatException("숫자가 아닌 값을 입력 할 수 없습니다.");
        }
    }
}
