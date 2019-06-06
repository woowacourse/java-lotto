package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.RankType;
import lotto.domain.WinStatistics;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printLottoTickets(final int amountOfCustoms, final int amountOfLottoTickets, final List<LottoTicket> lottoTickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", amountOfCustoms, amountOfLottoTickets - amountOfCustoms);

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printResult(Map<RankType, Integer> countOfResult) {
        System.out.println("당첨 통계\n" + "---------");

        printEachRank(countOfResult);
    }

    public static void printProfitRate(WinStatistics winStatistics, int money) {
        System.out.printf("총 수익률은 %.2f%%입니다.\n", winStatistics.calculateProfitRate(money));
    }

    public static void printNewLine() {
        System.out.println();
    }

    private static void printEachRank(Map<RankType, Integer> countOfResult) {
        for (RankType rankType : RankType.values()) {
            int matchingCount = rankType.getMatchingCount();
            int prize = rankType.getPrize();
            int count = countOfResult.get(rankType);

            if (rankType.equals(RankType.SECOND)) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n", matchingCount, prize, count);
                continue;
            }
            if (rankType.equals(RankType.NOTHING)) {
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n", matchingCount, prize, count);
        }
    }
}
