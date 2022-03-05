package lotto.model;

import java.util.Map;

public class RankCount {

    private final Map<Rank, Integer> rankCount;

    public RankCount(final Map<Rank, Integer> rankCount) {
        this.rankCount = rankCount;
    }

    public long calculateTotalMoney() {
        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += (long) rank.getPrice() * getEachRankCount(rank);
        }
        return sum;
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
