package lotto.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class RankCount {

    private static final int LOTTO_INITIAL_COUNT = 0;

    private final Map<Rank, Integer> rankCount;

    public RankCount(final Map<Rank, Integer> rankCount) {
        this.rankCount = new LinkedHashMap<>(rankCount);
    }

    public long calculateTotalMoney() {
        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += (long) rank.getPrice() * getEachRankCount(rank);
        }
        return sum;
    }

    public Integer getEachRankCount(final Rank rank) {
        if (rankCount.get(rank) == null) {
            return LOTTO_INITIAL_COUNT;
        }
        return rankCount.get(rank);
    }
}
