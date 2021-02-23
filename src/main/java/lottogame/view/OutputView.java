package lottogame.view;

import java.util.List;
import java.util.Map.Entry;
import lottogame.domain.LottoGameResult;
import lottogame.domain.Rank;

public class OutputView {

    private static final String DEFAULT_RANK_PRINT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String BONUS_RANK_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";

    public static void printLottoTickets(final List<String> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        for (String lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
        System.out.println();
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
        System.out.format("총 수익률은 %.2f입니다.", yield);
        System.out.println();
    }
}
