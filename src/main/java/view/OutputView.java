package view;

import domain.IssuedLotto;
import domain.IssuedLottos;
import domain.Rank;
import domain.Statistics;

public class OutputView {
    public static void showIssuedLottos(IssuedLottos autoIssuedLottos) {
        for (IssuedLotto issuedLotto : autoIssuedLottos.getLottos()) {
            System.out.println(issuedLotto);
       }
    }

    public static void showAnalysisOf(Statistics statistics) {
        for (Rank rank : Rank.values()) {
            System.out.println(rank.getRankExplanation() + " - " + statistics.countsOf(rank) + "개");
        }
        System.out.println("총 수익률은 " + statistics.calculateEarningRates() * 100 + "% 입니다.");
    }
}
