package lotto.model;

import java.util.Arrays;
import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> rankMap;

    public LottoStatistics(Map<Rank, Integer> rankMap) {
        this.rankMap = rankMap;
    }

    public Map<Rank, Integer> getRankMap() {
        return rankMap;
    }

    public double findRevenue(int amount) {
        double sum = Arrays.stream(Rank.values())
            .mapToInt(rank -> rank.sum(rankMap.get(rank)))
            .sum();

        return calculateRevenue(sum, amount);
    }

    private double calculateRevenue(double sum, int amount) {
        return sum / amount;
    }
}
