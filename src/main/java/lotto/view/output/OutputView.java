package lotto.view.output;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

public class OutputView {

    public static void outputLottoCount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.purchaseAutoCount() + "개를 구매했습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void outputResult(LottoResult lottoResult) {
        System.out.print(lottoResult);
        System.out.println("총 수익률은 " + lottoResult.yield() + "% 입니다.");
    }
}
