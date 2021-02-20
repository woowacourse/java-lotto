package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        printMatchCountAndReward(result);
        System.out.println(
            "총 수익률은 " + String.format("%.2f", result.calculateIncomeRate()) + "입니다.");
    }

    private void printMatchCountAndReward(LottoStatisticResult result) {
        List<Rank> ranks = new ArrayList<>(Arrays.asList(
            Rank.FIFTH,
            Rank.FOURTH,
            Rank.THIRD,
            Rank.SECOND,
            Rank.FIRST
        ));

        ranks.stream()
             .forEach(rank -> {
                 if (rank == Rank.SECOND) {
                     System.out.println("5개 일치, 보너스 볼 일치 (" + rank.getReward() + "원)- "
                         + result.getOrNoCount(rank) + "개");
                 }
                 if (rank != Rank.SECOND) {
                     System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getReward() + "원)- "
                         + result.getOrNoCount(rank) + "개");
                 }
             });
    }
}
