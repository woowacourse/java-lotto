package model;

import java.util.Map;

public class WinningStatistics {
    private final Map<WinningRank, Integer> statistics;

    public WinningStatistics(final Map<WinningRank, Integer> statistics) {
        this.statistics = statistics;
    }

    public Map<WinningRank, Integer> getStatistics() {
        return statistics;
    }

    public Double getLottoRateOfReturn(final int money) {
        double totalReturn = calculateTotalReturn();
        return totalReturn / (double) money;
    }

    private int calculateTotalReturn() {
        return statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
