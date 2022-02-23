package lotto;

import java.util.EnumMap;
import java.util.List;

public class Ranks {

    private final EnumMap<Rank, Integer> statistics;

    public Ranks(List<Rank> ranks) {
        this.statistics = initializeState();
        createStatistics(ranks);
    }

    public double getLottoTotalReward() {
        double total = 0;
        for (Rank rank : statistics.keySet()) {
            total += rank.calculateTotalReward(statistics.get(rank));
        }
        return total;
    }
    
    private EnumMap<Rank, Integer> initializeState() {
        EnumMap<Rank, Integer> temp = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            temp.put(rank, 0);
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
