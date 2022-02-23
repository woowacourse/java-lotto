package domain;

import java.util.Map;

public class Statistic {
    private final Map<Rank, Integer> statistics;

    public Statistic(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
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
