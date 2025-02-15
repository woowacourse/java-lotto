package lotto.view;

import static lotto.view.OutputMessage.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import lotto.domain.Rank;
import lotto.dto.response.LottosResponse;
import lotto.dto.response.ResultResponse;

public class OutputView {
    public void printTicket(LottosResponse response) {
        int size = response.lottos().size();
        System.out.printf(TICKET_COUNT_MESSAGE.getMessage(), size);
        response.lottos().stream()
            .map(LottosResponse.InnerLotto::numbers)
            .forEach(System.out::println);
        System.out.println();
    }

    public void printResult(ResultResponse response) {
        System.out.println(RESULT_TITLE_MESSAGE.getMessage());
        System.out.println(DIVIDER.getMessage());
        Arrays.stream(Rank.values())
            .sorted(Comparator.reverseOrder())
            .forEach(rank ->
                System.out.print(getRankCountMessage(rank, response.rankCount())));
        System.out.printf(RETURN_OF_RATE_MESSAGE.getMessage(), response.rateOfReturn());
    }

    private String getRankCountMessage(Rank rank, Map<Rank, Integer> rankCount) {
        if (rank == Rank.NONE) {
            return EMPTY.getMessage();
        }
        if (rank == Rank.SECOND) {
            return String.format(RANK_COUNT_MESSAGE_WITH_BONUS.getMessage(),
                rank.getMatchCount(), rank.getPrice(), rankCount.getOrDefault(rank, 0));
        }
        return String.format(RANK_COUNT_MESSAGE.getMessage(),
            rank.getMatchCount(), rank.getPrice(), rankCount.getOrDefault(rank, 0));
    }
}
