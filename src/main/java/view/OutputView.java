package view;

import domain.Lotto;
import domain.Rank;
import domain.Statistics;

import java.util.List;

public class OutputView {
    public static void showIssuedLottos(List<Lotto> autoIssuedLottos) {
        for (Lotto lotto : autoIssuedLottos) {
            System.out.println(lotto);
       }
    }

    public static void showAnalysisOf(Statistics statistics) {
        for (Rank rank : Rank.values()) {
            System.out.println(rank.getRankExplanation() + " - " + statistics.countsOf(rank) + "개");
        }
        System.out.println("총 수익률은 " + statistics.calculateEarningRates() * 100 + "% 입니다.");
    }
}
