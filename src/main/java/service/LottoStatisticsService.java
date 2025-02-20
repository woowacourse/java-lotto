package service;

import static java.util.stream.Collectors.toMap;

import domain.Lottos;
import domain.PrizeTier;
import dto.LottoStatisticsDto;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class LottoStatisticsService {

    public LottoStatisticsDto produceStatistics(Lottos lottos, int purchaseAmount) {
        Map<PrizeTier, Integer> prizeCounts = getPrizeTierCounts(lottos);
        long totalPrize = lottos.calculateTotalPrize();
        return createStatistics(prizeCounts, purchaseAmount, totalPrize);
    }

    private Map<PrizeTier, Integer> getPrizeTierCounts(Lottos lottos) {
        return Arrays.stream(PrizeTier.values())
            .collect(toMap(Function.identity(), lottos::countTiers));
    }

    private LottoStatisticsDto createStatistics(
        Map<PrizeTier, Integer> prizeCounts,
        int purchaseAmount,
        long totalPrize) {
        if (purchaseAmount == 0) {
            return new LottoStatisticsDto(prizeCounts, 0);
        }
        return new LottoStatisticsDto(prizeCounts, (double) totalPrize / purchaseAmount);
    }
}
