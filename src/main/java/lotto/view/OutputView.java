package lotto.view;

import lotto.model.Lotto;
import lotto.model.Payment;

import java.util.List;

public class OutputView {
        public static void printPurchaseNumber(Payment payment) {
                System.out.println(payment.getNumber() + "개를 구매했습니다.");
        }

        public static void printPurchaseLottos(List<Lotto> lottos) {
                for (Lotto lotto : lottos) {
                        System.out.println(lotto);
                }
                System.out.println();
        }

        public static void printWinStatsMessage() {
                System.out.println("당첨 통계");
                System.out.println("---------");
        }

        public static void printWinStats() {
        }
}
