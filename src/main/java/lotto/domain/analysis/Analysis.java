package lotto.domain.analysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.money.Money;
import lotto.domain.rank.Rank;

public class Analysis {

    private final Map<Rank, Long> rankCounts;
    private final double profitRate;

    public Analysis(final List<Rank> ranks, final Money money) {
        this.rankCounts = calculateRankCounts(ranks);
        this.profitRate = calculateProfitRate(ranks, money);
    }

    private Map<Rank, Long> calculateRankCounts(final List<Rank> ranks) {
        return ranks.stream()
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private double calculateProfitRate(final List<Rank> ranks, final Money money) {
        long totalSum = ranks.stream()
                .mapToLong(Rank::getPrizeMoney)
                .sum();
        return money.divide(totalSum);
    }

    public Map<Rank, Long> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
