package lottogame.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lottogame.domain.Rank;

public class OutputView {

    private static final String TICKETS_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String AMOUNT_YIELD_FORMAT = "총 수익률은 %.2f입니다.";

    public static void printLottoTickets(final List<String> lottoTickets) {
        System.out.println(lottoTickets.size() + TICKETS_COUNT_MESSAGE);
        for (String lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
        System.out.println();
    }

    public static void printLottoGameResult(final Map<Rank, Integer> ranks) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningResult(ranks);
    }

    private static void printWinningResult(final Map<Rank, Integer> ranks) {
        for (Entry<Rank, Integer> rank : ranks.entrySet()) {
            System.out.format(rank.getKey().getMessageFormat(), rank.getValue());
            System.out.println();
        }
    }

    public static void printLottoGameYield(final double yield) {
        System.out.format(AMOUNT_YIELD_FORMAT, yield);
        System.out.println();
    }
}
