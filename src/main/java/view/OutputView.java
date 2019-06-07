package view;

import domain.IssuedLotto;
import domain.Rank;
import domain.Statistics;

import java.util.List;

public class OutputView {
    public static void showIssuedLottos(List<IssuedLotto> autoIssuedLottos) {
        for (IssuedLotto issuedLotto : autoIssuedLottos) {
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
