package lotto.view;

import lotto.model.Payment;

public class OutputView {
        public static void printPurchaseNumber(Payment payment) {
                System.out.println(payment.getNumber() + "개를 구매했습니다.");
        }
}
