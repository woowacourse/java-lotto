package domain;

import java.util.*;

public class Statistic {
    private final Map<Rank, Integer> statistics;

    private Statistic(List<Rank> ranks) {
        this.statistics = new EnumMap<>(Rank.class);
        initStatistics();
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    private void initStatistics() {
        Arrays.stream(Rank.values()).forEach(rank -> statistics.put(rank, 0));
    }

    public Statistic(Map<Rank, Integer> statistics) {
        this.statistics = new EnumMap<>(statistics);
    }

    public static Statistic valueOf(List<Rank> ranks) {
        return new Statistic(ranks);
    }

    public double getProfitRate(Money money) {
        double totalWinning = statistics.entrySet()
                .stream()
                .mapToDouble(rankIntegerEntry -> rankIntegerEntry.getKey().getWinningPrice() * rankIntegerEntry.getValue())
                .sum();
        return totalWinning / money.getMoney();
    }

    public Map<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }
}
