package lotto.domain;

import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;

public class OutputView {

    public static void printPurchasedLottoResult(PurchasedLottos purchasedLottos, int manualLottoSize) {
        System.out.println("수동으로" + manualLottoSize + "장, 자동으로 " + (purchasedLottos.size() - manualLottoSize) + "개를 구매했습니다.");
        for (Lotto lotto: purchasedLottos.getLottos()) {
            System.out.println(lotto.toString());
        }
    }
}
