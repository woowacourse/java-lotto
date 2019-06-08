package view;

import domain.IssuedLotto;
import domain.IssuedLottos;
import domain.Rank;
import domain.Statistics;

public class OutputView {
    public static void showIssuedLottos(IssuedLottos manualIssuedLottos, IssuedLottos autoIssuedLottos) {
        System.out.println("수동으로 " + manualIssuedLottos.size() + "장, 자동으로 "
                + autoIssuedLottos.size() + "장을 구했습니다.");
        printAllLottosIn(manualIssuedLottos);
        printAllLottosIn(autoIssuedLottos);
    }

    private static void printAllLottosIn(IssuedLottos manualIssuedLottos) {
        for (IssuedLotto issuedLotto : manualIssuedLottos.getLottos()) {
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
