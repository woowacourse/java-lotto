package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Statistics {

    private final Map<Rank, Integer> result;

    public Statistics(Map<Rank, Integer> result) {
        this.result = result;
    }

    public static Statistics of(List<Rank> ranks) {
        return new Statistics(calculate(ranks));
    }

    private static Map<Rank, Integer> calculate(List<Rank> ranks) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank value : Rank.values()) {
            result.put(value, value.findRewardCount(ranks));
        }
        return result;
    }

    public double calculateProfitRate(double inputMoney) {
        return sumTotal() / inputMoney;
    }

    public Map<Rank, Integer> getResult() {
        return new EnumMap<>(result);
    }

    private long sumTotal() {
        return result.keySet().stream()
            .mapToLong(rank -> rank.getReward() * result.get(rank))
            .sum();
    }
}
