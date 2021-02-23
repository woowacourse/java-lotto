package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {
    private static final String MANUAL_LOTTO_NUMBER_QUESTION = "수동으로 구매할 번호를 입력해 주세요.";

    public void printPurchasingLotto(int manualLottoCount, int autoLottoCount) {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        sb.append("수동으로 ")
          .append(manualLottoCount)
          .append("장, 자동으로 ")
          .append(autoLottoCount)
          .append("개를 구매했습니다.");
        System.out.println(sb.toString());
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
        List<Rank> ranks = Arrays.asList(
            Rank.FIFTH,
            Rank.FOURTH,
            Rank.THIRD,
            Rank.SECOND,
            Rank.FIRST
        );

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

    public void printInputManualLottoNumbers() {
        System.out.println(MANUAL_LOTTO_NUMBER_QUESTION);
    }
}
