package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class Ranks {

    private final EnumMap<Rank, Integer> statistics;

    public Ranks(List<Rank> ranks) {
        this.statistics = new EnumMap<>(Rank.class);
        createStatistics(ranks);
    }

    private void createStatistics(List<Rank> ranks) {
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
    }

    public EnumMap<Rank, Integer> getStatistics() {
        EnumMap<Rank, Integer> statisticsResult = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statisticsResult.put(rank, this.statistics.getOrDefault(rank, 0));
        }
        return statisticsResult;
    }

    public double getLottoTotalReward() {
        double total = 0;
        for (Rank rank : statistics.keySet()) {
            total += rank.calculateTotalReward(statistics.get(rank));
        }
        return total;
    }
}
