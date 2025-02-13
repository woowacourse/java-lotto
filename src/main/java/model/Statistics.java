package model;

import java.util.Map;

public class Statistics {
    private Map<PrizeTier, Integer> prizeCounts;
    private long totalPrize;
    private double profitRate;

    public Statistics(Map<PrizeTier, Integer> prizeCounts, long totalPrize, double profitRate) {
        this.prizeCounts = prizeCounts;
        this.totalPrize = totalPrize;
        this.profitRate = profitRate;
    }
}
