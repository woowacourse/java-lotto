package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoStatistics {

    private final Map<LottoRank, Integer> rankCounts;
    private final double profitRate;

    public LottoStatistics(Map<LottoRank, Integer> rankCounts, double profitRate) {
        this.rankCounts = rankCounts;
        this.profitRate = profitRate;
    }

    public LottoStatistics(List<LottoRank> ranks, Money purchasedMoney) {
        this(countFrequency(ranks),
            calculateProfit(ranks, purchasedMoney));
    }

    private static Map<LottoRank, Integer> countFrequency(List<LottoRank> ranks) {
        return Arrays.stream(LottoRank.values())
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    () -> new TreeMap<>(),
                    Collectors.summingInt(rank -> Collections.frequency(ranks, rank))
                )
            );
    }

    private static double calculateProfit(List<LottoRank> ranks, Money purchasedMoney) {
        double sum = ranks.stream()
            .mapToDouble(LottoRank::getPrize)
            .sum();
        return sum / purchasedMoney.getAmount();
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return rankCounts;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
