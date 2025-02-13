package factory;

import java.util.Map;
import model.PrizeTier;
import model.Statistics;

public class StatisticsFactory {
    public static Statistics createStatistics(Map<PrizeTier, Integer> prizeCounts, double profitRate) {
        return new Statistics(prizeCounts, profitRate);
    }
}
