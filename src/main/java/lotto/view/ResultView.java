package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;

public class ResultView {

    public static final String LOTTO_BUYING_MESSAGE = "개를 구매했습니다.";

    public static void printResult(Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.println(lottos.getCount() + LOTTO_BUYING_MESSAGE);
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
