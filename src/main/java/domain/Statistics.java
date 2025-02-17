package domain;

import java.util.Collections;
import java.util.Map;

public class Statistics {

    private final Map<PrizeTier, Integer> prizeCounts;
    private final double profitRate;

    public Statistics(Map<PrizeTier, Integer> prizeCounts, double profitRate) {
        this.prizeCounts = prizeCounts;
        this.profitRate = profitRate;
    }

    public Map<PrizeTier, Integer> getPrizeCounts() {
        return Collections.unmodifiableMap(prizeCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
