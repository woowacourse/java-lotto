package lotto.view.outputview;

import lotto.domain.lottofactory.LottoAmount;

public class OutputView {
    public static void printAmount(LottoAmount lottoAmount) {
        System.out.println(lottoAmount.getLottoAmount() + "개를 구매했습니다.");
    }
}
