package lotto.domain.ticket;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.domain.rank.Rank;

public class Analysis {

    private final Map<Rank, Integer> rankCounts;
    private final double profitRate;

    public Analysis(final List<Rank> ranks, final int ticketCount) {
        this.rankCounts = calculateRankCounts(ranks);
        this.profitRate = calculateProfitRate(ranks, ticketCount);
    }

    private Map<Rank, Integer> calculateRankCounts(final List<Rank> ranks) {
        final Map<Rank, Integer> rankMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            final int count = Collections.frequency(ranks, rank);
            rankMap.put(rank, count);
        }
        return rankMap;
    }

    private double calculateProfitRate(final List<Rank> ranks, final int ticketCount) {
        long total = ranks.stream()
                .mapToLong(Rank::getPrizeMoney)
                .sum();
        return (double) total / ticketCount;
    }

    public Map<Rank, Integer> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
