package lotto.view;

import java.util.Arrays;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.result.Count;
import lotto.domain.result.GameResult;
import lotto.domain.result.Rank;

public class OutputView {
    public static void printPieces(int parseToPiece) {
        System.out.println(parseToPiece + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public static void printResult(GameResult gameResult) {
        System.out.println("\n당첨 통계 \n ==============");

        printRank(gameResult.getStatistic());
        printProfit(gameResult.getProfit());
    }

    private static void printRank(Map<Rank, Count> statistic) {
        Arrays.stream(Rank.values())
				.filter(Rank::isNotDefault)
				.forEach(rank -> printEachRank(rank, statistic.get(rank)));
    }

    private static void printEachRank(Rank rank, Count count) {
        StringBuilder sb = new StringBuilder();
        if (checkBonus(rank, count))
            return;
        sb.append(rank.getMatchingNumbers())
                .append("개 일치 (")
                .append(rank.getPrize())
                .append("원) --")
                .append(count.getCount())
                .append("개");
        System.out.println(sb.toString());
    }

    private static void printSecond(Rank rank, Count count) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchingNumbers())
                .append("개 일치, 보너스 볼 일치 (")
                .append(rank.getPrize())
                .append("원) --")
                .append(count.getCount())
                .append("개");
        System.out.println(sb.toString());
    }

    private static boolean checkBonus(Rank rank, Count count) {
        if (rank.isBonusMatching()) {
            printSecond(rank, count);
            return true;
        }
        return false;
    }

    private static void printProfit(double profit) {
        System.out.println("총 수익률은 " + profit + "% 입니다.");
    }
}
