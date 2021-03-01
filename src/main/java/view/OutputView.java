package view;

import domain.lotto.LottoNumber;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.rank.Rank;
import domain.Wallet;
import domain.rank.Ranks;
import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WINNING_STATISTICS_TITLE = "당첨 통계";
    private static final String DIVIDER = "---------";
    private static final String TICKET_QUANTITY_FORMAT = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String RANK_RESULT_FORMAT = "%d개 일치 %s(%d원) - %d개";
    private static final String BONUS_BALL_FORMAT = ", 보너스 볼 일치";

    public static void transactionInfo(Wallet wallet) {
        System.out.println(
                String.format(TICKET_QUANTITY_FORMAT,
                        wallet.getManualQuantity(),
                        wallet.getAutoQuantity())
        );
    }

    public static void ticketInfo(LottoTickets lottoTickets) {
        lottoTickets.toList()
                .forEach(ticket -> System.out.println(lottoNumberInfo(ticket)));
    }

    private static String lottoNumberInfo(final LottoTicket lottoTicket) {
        String numbers = lottoTicket.toList().stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + numbers + "]";
    }

    public static void result(Ranks ranks) {
        System.out.println(WINNING_STATISTICS_TITLE);
        System.out.println(DIVIDER);
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.equalsNothing())
                .forEach(rank -> rankInfo(ranks.count(rank), rank));
    }

    public static void rankInfo(final int count, final Rank rank) {
        String bonusMessage = "";
        if (rank == Rank.SECOND) {
            bonusMessage = BONUS_BALL_FORMAT;
        }
        System.out.println(
                String.format(
                        RANK_RESULT_FORMAT, rank.getMatching(), bonusMessage, rank.getMoney(), count
                )
        );
    }

    public static void totalProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "입니다.");
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
