package lotto.view;

import java.util.Collections;
import java.util.List;

public class OutputView {

    public static void printCountMessage(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(List<Integer> lottos) {
        Collections.sort(lottos);
        System.out.println(lottos);
    }

}
