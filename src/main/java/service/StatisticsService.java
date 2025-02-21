package service;

import java.util.Map;
import model.Lotto;
import model.LottoResults;
import model.PrizeTier;
import model.Statistics;

public class StatisticsService {
    public Statistics produceStatistics(LottoResults lottoResults) {
        Map<PrizeTier, Integer> prizeCounts = lottoResults.getPrizeTierCounts();

        long totalPrize = lottoResults.getTotalPrize();

        double profitRate = totalPrize / (double) Lotto.PRICE * lottoResults.getResultCount();

        return new Statistics(prizeCounts, profitRate);

    }
}
