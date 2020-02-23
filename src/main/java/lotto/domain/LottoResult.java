package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class LottoResult {
    private final List<Rank> ranks;

    public LottoResult() {
        this.ranks = Arrays.asList(Rank.values());
    }

    public void plusTicketCount(Rank rankToPlusCount) {
        for (Rank rank : ranks) {
            if (rank == rankToPlusCount) {
                rank.plusTicketCount();
            }
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
            builder.append(rank.toString() + "\n");
        }
        return builder.toString();
    }
}
