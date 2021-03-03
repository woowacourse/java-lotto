package lotto.view;

import lotto.lottogame.LottoCount;
import lotto.lottoticket.LottoTicket;
import lotto.lottoticket.LottoTickets;
import lotto.ranking.Ranking;
import lotto.ranking.Statistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String NOTICE_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String STATISTICS_TITLE = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---------";

    private static final String RANKING_RESULT_FORMAT = "%d개 일치 (%d원)- %d개%n";
    private static final String RANKING_SECOND_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원)- %d개%n";
    private static final String TOTAL_PROFIT_FORMAT = "총 수익률은 %s 입니다.";

    private OutputView() {
    }

    public static void noticeLottoCount(LottoCount manualLottoCount, LottoCount autoLottoCount) {
        System.out.println();
        System.out.printf(NOTICE_LOTTO_COUNT, manualLottoCount.getLottoCount(), autoLottoCount.getLottoCount());
    }

    public static void showTickets(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getTickets()) {
            System.out.println(lottoTicket.getTicket());
        }
    }

    public static void noticeStatistics(Statistics statistics, Double profit) {
        System.out.println();
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_DIVIDER);
        printRankings(statistics, profit);
    }

    private static void printRankings(Statistics statistics, Double profit) {
        Map<Ranking, Integer> result = statistics.getStatistics();
        List<Ranking> rankings = Arrays.asList(Ranking.values());
        Collections.reverse(rankings);
        for (Ranking ranking : rankings) {
            printEachRanking(result, ranking);
        }
        showProfit(profit);
    }

    private static void printEachRanking(Map<Ranking, Integer> result, Ranking ranking) {
        if (ranking.equals(Ranking.NOTHING)) {
            return;
        }
        if (ranking.equals(Ranking.SECOND)) {
            System.out.printf(RANKING_SECOND_RESULT_FORMAT, ranking.getMatchCount(), ranking.getPrice(), result.get(ranking));
            return;
        }
        System.out.printf(RANKING_RESULT_FORMAT, ranking.getMatchCount(), ranking.getPrice(), result.get(ranking));
    }

    public static void showProfit(Double calculateProfit) {
        System.out.printf(TOTAL_PROFIT_FORMAT, calculateProfit);
        System.out.println();
    }

    public static void printError(IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
}