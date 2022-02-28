package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Integer> statistics;

    public Ranks(List<Rank> ranks) {
        this.statistics = new EnumMap<>(Rank.class);
        createStatistics(ranks);
    }

    private void createStatistics(List<Rank> ranks) {
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
    }

    public Map<Rank, Integer> getStatistics() {
        Map<Rank, Integer> statisticsResult = new EnumMap<>(Rank.class);
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
