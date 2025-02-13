package service;

import java.util.HashMap;
import java.util.Map;
import model.Lottos;
import model.PrizeTier;
import model.Statistics;

public class StatisticsService {
    public Statistics produceStatistics(Lottos lottos, int purchaseAmount) {
        Map<PrizeTier, Integer> prizeCounts = new HashMap<>();
        for (PrizeTier prizeTier : PrizeTier.values()) {
            int count = lottos.countTiers(prizeTier);
            prizeCounts.put(prizeTier, count);
        }

        long totalPrize = lottos.calculateTotalPrize();

        if (purchaseAmount == 0) {
            return new Statistics(prizeCounts, 0);
        }

        double profitRate = (double) totalPrize / purchaseAmount;
        return new Statistics(prizeCounts, profitRate);
    }
}
