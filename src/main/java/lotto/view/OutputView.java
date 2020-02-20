package lotto.view;

import lotto.domain.Lotties;
import lotto.domain.PaidPrice;

public class OutputView {
    public static void printLottoCount(PaidPrice paidPrice) {
        System.out.println(paidPrice.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottiesNumbers(Lotties lotties) {
        int lottiesLength = lotties.getLotties().size();
        for (int i = 0; i < lottiesLength; i++) {
            System.out.println(lotties.getLotties());
        }
    }
}
