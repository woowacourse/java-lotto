package dto;

import java.util.Map;
import domain.PrizeTier;

public class StatisticsDto {

    private Map<PrizeTier, Integer> prizeCounts;
    private double profitRate;

    public StatisticsDto(Map<PrizeTier, Integer> prizeCounts, double profitRate) {
        this.prizeCounts = prizeCounts;
        this.profitRate = profitRate;
    }

    public Map<PrizeTier, Integer> getPrizeCounts() {
        return prizeCounts;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
