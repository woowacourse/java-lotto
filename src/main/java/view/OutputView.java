package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.Ranking;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WINNING_STATISTICS_TITLE = "당첨 통계";
    private static final String DIVIDER = "---------";
    private static final String NUMBER_OF_TICKETS = "개를 구매했습니다.";
    private static final String COMMA = ", ";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String RANK_RESULT_FORMAT = "%d개 일치 %s(%d원) - %d개";
    private static final String TOTAL_PROFIT_RATE_PREFIX = "총 수익률은 ";
    private static final String TOTAL_PROFIT_RATE_SUFFIX = "입니다.";
    private static final String BONUS_BALL_FORMAT = ", 보너스 볼 일치";
    private static final String EMPTY_STRING = "";

    public static void printNumberOfTickets(final int lottoQuantity) {
        System.out.println(lottoQuantity + NUMBER_OF_TICKETS);
    }

    public static void printLottoTickets(final List<LottoTicket> lottoTickets) {
        lottoTickets.forEach(ticket -> System.out.println(printLottoNumbers(ticket)));
    }

    private static String printLottoNumbers(final LottoTicket lottoTicket) {
        String numbers = lottoTicket.toList().stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(COMMA));
        return LEFT_BRACKET + numbers + RIGHT_BRACKET;
    }

    public static void printRankResultTitle() {
        System.out.println(WINNING_STATISTICS_TITLE);
        System.out.println(DIVIDER);
    }

    public static void printIndividualRankResult(final int countNumberOfRank,
            final Ranking ranking) {
        String bonusMessage = EMPTY_STRING;
        if (ranking == Ranking.SECOND) {
            bonusMessage = BONUS_BALL_FORMAT;
        }

        String print = String
                .format(RANK_RESULT_FORMAT, ranking.getMatching(), bonusMessage, ranking.getMoney(),
                        countNumberOfRank);
        System.out.println(print);
    }

    public static void printTotalProfitRate(double profitRate) {
        System.out.println(TOTAL_PROFIT_RATE_PREFIX + profitRate + TOTAL_PROFIT_RATE_SUFFIX);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
