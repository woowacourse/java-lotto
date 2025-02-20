package dto;

import domain.PrizeTier;
import java.util.Map;

public class LottoStatisticsDto {

    private final Map<PrizeTier, Integer> prizeCounts;
    private final double profitRate;

    public LottoStatisticsDto(Map<PrizeTier, Integer> prizeCounts, double profitRate) {
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
