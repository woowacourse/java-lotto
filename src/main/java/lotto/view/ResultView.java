package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;

public class ResultView {

    public static void printResult(Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.println(lottos.getCount() + "개를 구매했습니다.");
    }

    private static void printBuyingLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printBuyingLotto(lotto);
        }
    }

    private static void printBuyingLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }
}
