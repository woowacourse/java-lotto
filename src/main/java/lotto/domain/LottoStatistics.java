package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class LottoStatistics {

    public static final int NON_COUNT = 0;
    
    private final EnumMap<Rank, Integer> statistics;

    public LottoStatistics(List<Rank> ranks) {
        this.statistics = initializeState();
        createStatistics(ranks);
    }

    public double getLottoTotalReward() {
        return statistics.keySet().stream()
                .mapToDouble(rank -> rank.calculateTotalReward(statistics.get(rank)))
                .sum();
    }

    private EnumMap<Rank, Integer> initializeState() {
        EnumMap<Rank, Integer> temp = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            temp.put(rank, NON_COUNT);
        }
        return temp;
    }

    private void createStatistics(List<Rank> ranks) {
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public EnumMap<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }
}
