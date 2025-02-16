package model;

import dto.StatisticsDto;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private Map<PrizeTier, Integer> prizeCounts;
    private double profitRate;

    public Statistics(Map<PrizeTier, Integer> prizeCounts, double profitRate) {
        this.prizeCounts = prizeCounts;
        this.profitRate = profitRate;
    }

    public StatisticsDto toDto() {
        return new StatisticsDto(new HashMap<>(prizeCounts), profitRate);
    }
}
