package service;

import static java.util.stream.Collectors.toMap;

import domain.Lottos;
import domain.PrizeTier;
import dto.Statistics;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class StatisticsService {

    public Statistics produceStatistics(Lottos lottos, int purchaseAmount) {
        Map<PrizeTier, Integer> prizeCounts = getPrizeTierCounts(lottos);
        long totalPrize = lottos.calculateTotalPrize();
        return createStatistics(prizeCounts, purchaseAmount, totalPrize);
    }

    private Map<PrizeTier, Integer> getPrizeTierCounts(Lottos lottos) {
        return Arrays.stream(PrizeTier.values())
            .collect(toMap(Function.identity(), lottos::countTiers));
    }

    private Statistics createStatistics(
        Map<PrizeTier, Integer> prizeCounts,
        int purchaseAmount,
        long totalPrize) {
        if (purchaseAmount == 0) {
            return new Statistics(prizeCounts, 0);
        }
        return new Statistics(prizeCounts, (double) totalPrize / purchaseAmount);
    }
}
