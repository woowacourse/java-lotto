package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String TICKET_COUNT_MESSAGE = "%d개를 구매했습니다.%n";
    public static final String RESULT_TITLE_MESSAGE = "당첨 통계";
    public static final String DIVIDER = "---------";
    public static final String RANK_COUNT_MESSAGE_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원) - %d개%n";
    public static final String RANK_COUNT_MESSAGE = "%d개 일치 (%d원)- %d개%n";
    public static final String RETURN_OF_RATE_MESSAGE = "총 수익률은 %.2f입니다.%n";
    public static final String EMPTY = "";

    public void printTicket(List<Lotto> lottos) {
        int size = lottos.size();
        System.out.printf(TICKET_COUNT_MESSAGE, size);
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public void printResult(Map<Rank, Integer> rankCount, double rateOfReturn) {
        System.out.println(RESULT_TITLE_MESSAGE);
        System.out.println(DIVIDER);
        Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .forEach(rank ->
                    System.out.print(getRankCountMessage(rank, rankCount)));
        System.out.printf(RETURN_OF_RATE_MESSAGE, rateOfReturn);
    }

    private String getRankCountMessage(Rank rank, Map<Rank, Integer> rankCount) {
        if (rank == Rank.NONE) {
            return EMPTY;
        }
        if (rank == Rank.SECOND) {
            return String.format(RANK_COUNT_MESSAGE_WITH_BONUS,
                    rank.getMatchCount(), rank.getPrice(), rankCount.getOrDefault(rank, 0));
        }
        return String.format(RANK_COUNT_MESSAGE,
                rank.getMatchCount(), rank.getPrice(), rankCount.getOrDefault(rank, 0));
    }
}
