package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Payment;

public class OutputView {
    public static void printLottoCount(Payment payment) {
        System.out.println(payment.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottiesNumbers(Lottos lottos) {
        int lottiesLength = lottos.getLottos().size();
        for (int i = 0; i < lottiesLength; i++) {
            System.out.println(lottos.getLottos());
        }
    }
}
