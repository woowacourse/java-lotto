package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;

import java.util.List;

public class OutputView {
    public static void showLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.getNumberOfLotto());
        for(Lotto lotto : lottos.getLottos()){
            System.out.println(lotto.getNumbers());
        }
    }

    public static void result(List<Result> results) {
    }
}
