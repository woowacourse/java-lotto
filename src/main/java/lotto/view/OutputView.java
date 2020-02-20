package lotto.view;

import lotto.domain.Lotties;
import lotto.domain.Lotto;
import lotto.domain.Payment;

import java.util.List;

public class OutputView {
    public static void printLottoCount(Payment payment) {
        System.out.println(payment.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottiesNumbers(Lotties lotties) {
        int lottiesLength = lotties.getLotties().size();
        for (int i = 0; i < lottiesLength; i++) {
            System.out.println(lotties.getLotties());
        }
    }
}
