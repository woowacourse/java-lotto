package lotto.view.output;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;

public class OutputView {

    public static void outputLottoCount(Money money) {
        System.out.println(money.purchaseCount() + "개를 구매했습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }
}
