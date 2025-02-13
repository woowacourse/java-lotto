package lotto.view;

import lotto.domain.Rank;
import lotto.dto.response.LottosResponse;
import lotto.dto.response.ResultResponse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {
    private static final String TICKET_COUNT_MESSAGE = "%d개를 구매했습니다.%n";
    private static final String RESULT_TITLE_MESSAGE = "당첨 통계";
    private static final String DIVIDER = "---------";
    private static final String RANK_COUNT_MESSAGE_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원) - %d개%n";
    private static final String RANK_COUNT_MESSAGE = "%d개 일치 (%d원)- %d개%n";
    private static final String RETURN_OF_RATE_MESSAGE = "총 수익률은 %.2f입니다.%n";
    private static final String EMPTY = "";

    public void printTicket(LottosResponse response) {
        int size = response.lottos().size();
        System.out.printf(TICKET_COUNT_MESSAGE, size);
        response.lottos().stream()
            .map(LottosResponse.InnerLotto::numbers)
            .forEach(System.out::println);
    }

    public void printResult(ResultResponse response) {
        System.out.println(RESULT_TITLE_MESSAGE);
        System.out.println(DIVIDER);
        Arrays.stream(Rank.values())
            .sorted(Comparator.reverseOrder())
            .forEach(rank ->
                System.out.print(getRankCountMessage(rank, response.rankCount())));
        System.out.printf(RETURN_OF_RATE_MESSAGE, response.rateOfReturn());
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
