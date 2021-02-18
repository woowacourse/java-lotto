package lottogame.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lottogame.domain.Rank;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class OutputView {

    public static final String TICKETS_COUNT_MSG = "개를 구매했습니다.";
    public static final String AMOUNT_YIELD_FORMAT = "총 수익률은 %.2f입니다.";

    public static void printLottoTickets(final LottoTickets lottoTickets) {
        System.out.println(lottoTickets.getTicketsCount() + TICKETS_COUNT_MSG);
        for (LottoTicket lottoTicket : lottoTickets.toList()) {
            printLottoNumbers(lottoTicket);
        }
        System.out.println();
    }

    public static void printLottoNumbers(final LottoTicket lottoTicket) {
        List<String> lottoNumbers = lottoTicket.getLottoNumbers()
            .stream()
            .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
            .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("[" + String.join(", ", lottoNumbers) + "]");
    }

    public static void printLottoGameResult(final Map<Rank, Integer> ranks) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningResult(ranks);
    }

    private static void printWinningResult(Map<Rank, Integer> ranks) {
        for (Entry<Rank, Integer> rank : ranks.entrySet()) {
            int rankCount = rank.getValue();
            System.out.format(rank.getKey().getMessageFormat(), rankCount);
            System.out.println();
        }
    }

    public static void printLottoGameYield(final double yield) {
        System.out.format(AMOUNT_YIELD_FORMAT, yield);
        System.out.println();
    }
}
