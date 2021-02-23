package lottogame.view;

import java.util.List;
import java.util.Map.Entry;
import lottogame.domain.LottoGameResult;
import lottogame.domain.Rank;

public class OutputView {

    private static final String TICKETS_COUNT_PRINT_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String DEFAULT_RANK_PRINT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String BONUS_RANK_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";

    public static void printLottoTickets(final List<String> manualTickets, final List<String> autoTickets) {
        System.out.println(String.format(TICKETS_COUNT_PRINT_FORMAT, manualTickets.size(), autoTickets.size()));
        printTickets(manualTickets);
        printTickets(autoTickets);
        System.out.println();
    }

    private static void printTickets(List<String> tickets) {
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public static void printLottoGameResult(final LottoGameResult lottoGameResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Entry<Rank, Integer> rank : lottoGameResult.getRanks().entrySet()) {
            System.out.println(printLottoRank(rank.getKey(), rank.getValue()));
        }
    }

    private static String printLottoRank(Rank rank, Integer count) {
        if (rank.hasBonus()) {
            return String.format(BONUS_RANK_PRINT_FORMAT,
                rank.getMatchCount(), rank.getPrice(), count);
        }
        return String.format(DEFAULT_RANK_PRINT_FORMAT,
            rank.getMatchCount(), rank.getPrice(), count);
    }

    public static void printLottoGameYield(final double yield) {
//        System.out.format("총 수익률은 %.2f입니다.", yield);
//        System.out.println();
        System.out.println(String.format("총 수익률은 %.2f입니다.", yield));
    }
}
