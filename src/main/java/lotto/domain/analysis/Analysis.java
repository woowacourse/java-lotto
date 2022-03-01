package lotto.domain.analysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.winning.Rank;
import lotto.utils.MoneyUnit;

public class Analysis {

    private final Map<Rank, Long> rankCounts;
    private final double profitRate;

    public Analysis(final List<Rank> ranks, final int ticketCount) {
        this.rankCounts = calculateRankCounts(ranks);
        this.profitRate = calculateProfitRate(ranks, ticketCount);
    }

    private Map<Rank, Long> calculateRankCounts(final List<Rank> ranks) {
        return ranks.stream()
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private double calculateProfitRate(final List<Rank> ranks, final int ticketCount) {
        long total = ranks.stream()
                .mapToLong(Rank::getPrizeMoney)
                .sum();
        return (double) total / MoneyUnit.multiple(ticketCount);
    }

    public Map<Rank, Long> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
