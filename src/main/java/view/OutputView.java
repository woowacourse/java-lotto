package view;

import domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String COMMA_WITH_BLANK = ", ";
    private static final String MESSAGE_STATISTICS = "당첨 통계\n--------\n";
    private static final String STATISTICS_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String STATISTICS_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_PROFIT_FORMAT = "총 수익률은 %.2f입니다.";

    private static OutputView instance;

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
        }
        return instance;
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLottoTicket(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            String numbers = lottoTicket.numbers().stream()
                    .map(LottoNumber::toString)
                    .collect(Collectors.joining(COMMA_WITH_BLANK));
            System.out.println(LEFT_BRACKET + numbers + RIGHT_BRACKET);
        }
    }

    public void newLine() {
        System.out.println();
    }

    public void printStatistics(Statistics statistics) {
        Map<Rank, Integer> result = statistics.result();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        System.out.print(MESSAGE_STATISTICS);
        for (Rank rank: ranks) {
            printMatchCount(rank, result.get(rank));
        }
    }

    private void printMatchCount(Rank rank, long count) {
        if (rank.equals(Rank.SECOND)) {
            System.out.printf((STATISTICS_BONUS_FORMAT) + "%n", rank.getMatch(), rank.getReward().toLong(), count);
            return;
        }
        if (rank.equals(Rank.NOTHING)) {
            return;
        }
        System.out.printf((STATISTICS_FORMAT) + "%n", rank.getMatch(), rank.getReward().toLong(), count);
    }

    public void printProfit(Profit profit) {
        System.out.printf((MESSAGE_PROFIT_FORMAT) + "%n", profit.toDouble());
    }
}
