package view;

import domain.*;

public class OutputView {
    public static void showIssuedLottos(IssuedLottos manualIssuedLottos, IssuedLottos autoIssuedLottos) {
        System.out.println("수동으로 " + manualIssuedLottos.size() + "장, 자동으로 "
                + autoIssuedLottos.size() + "장을 구했습니다.");
        printAllLottosIn(manualIssuedLottos);
        printAllLottosIn(autoIssuedLottos);
    }

    private static void printAllLottosIn(IssuedLottos manualIssuedLottos) {
        for (Lotto lotto : manualIssuedLottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void showAnalysisOf(Statistics statistics, double earningRates) {
        for (Rank rank : Rank.values()) {
            showRankStatisticsOf(statistics, rank);
        }
        System.out.println("총 수익률은 " + earningRates * 100 + "% 입니다.");
    }

    private static void showRankStatisticsOf(Statistics statistics, Rank rank) {
        if (rank == Rank.SECOND) {
            System.out.println(rank.getNumberOfMatching() + "개 일치, 보너스번호 일치 ("
                    + rank.getWinningMoney().getAmount() + "원) - " + statistics.countsOf(rank) + "개");
            return;
        }
        System.out.println(rank.getNumberOfMatching() + "개 일치 (" + rank.getWinningMoney().getAmount() + "원) - "
                + statistics.countsOf(rank) + "개");
    }

    public static void show404NotFound() {
        System.out.println("404 not found");
    }
}
