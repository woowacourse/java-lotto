package lotto.view;

import java.util.Collections;
import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    public void printPurchasingLotto(int purchasingLottoCount) {
        System.out.println(purchasingLottoCount + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        lottos.toInts()
              .forEach(lotto -> {
                  Collections.sort(lotto);
                  System.out.println(lotto);
              });

        System.out.println();
    }

    public void printStatisticResult(LottoStatisticResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + result.getOrNoCount(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원)- " + result.getOrNoCount(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원)- " + result.getOrNoCount(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + result.getOrNoCount(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.getOrNoCount(Rank.FIRST) + "개");
        System.out.println(
            "총 수익률은 " + String.format("%.2f", result.calculateIncomeRate()) + "입니다.");
    }
}
