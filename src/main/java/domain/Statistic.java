package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Statistic {
    private final Map<Rank, Integer> statistics;

    private Statistic() {
        statistics = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> statistics.put(rank, 0));
    }

    public Statistic(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public static Statistic initStatistic() {
        return new Statistic();
    }

    public void add(Rank rank) {
        statistics.put(rank, statistics.get(rank) + 1);
    }

    public double getProfitRate(Money money) {
        double totalWinning = statistics.entrySet()
                .stream()
                .mapToDouble(rankIntegerEntry -> rankIntegerEntry.getKey().getWinningPrice() * rankIntegerEntry.getValue())
                .sum();
        return totalWinning / money.getMoney();
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
