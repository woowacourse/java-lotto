package lotto.view;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private static void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }
}
