package lotto.domain;

import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoResult {
    private final List<Rank> ranks;

    public LottoResult() {
        this.ranks = Arrays.asList(Rank.values());
    }

    public void updateResult(Rank rankToPlusCount) {
        for (Rank rank : ranks) {
            updateTicketCount(rankToPlusCount, rank);
        }
    }

    private void updateTicketCount(Rank rankToPlusCount, Rank rank) {
        if (rank == rankToPlusCount) {
            rank.plusTicketCount();
        }
    }

    public long calculateTotalReward() {
        return ranks.stream()
                .map(x -> x.getTicketCount() * x.getReward())
                .reduce(Long::sum)
                .orElse(0L);
    }

    public String getResult() {
        StringBuilder builder = new StringBuilder();
        for (Rank rank : ranks) {
            builder.append(rank.toString() + OutputView.NEW_LINE);
        }
        return builder.toString();
    }
}
