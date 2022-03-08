package lotto.dto.result;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.Rank;

public class StatisticsResult {

    private final Map<String, RankResult> statistics;
    private final double profitRate;

    public StatisticsResult(Map<Rank, Integer> statistics, double profitRate) {
        this.statistics = statisticsToStatisticsResult(statistics);
        this.profitRate = profitRate;
    }

    public Map<String, RankResult> getStatistics() {
        return statistics;
    }

    public double getProfitRate() {
        return profitRate;
    }

    private Map<String, RankResult> statisticsToStatisticsResult(Map<Rank, Integer> result) {
        Map<String, RankResult> rankResult = new HashMap<>();
        for (Rank rank : result.keySet()) {
            rankResult.put(rank.name(), new RankResult(rank.getMatchCount(), rank.getReward(), result.get(rank)));
        }
        return rankResult;
    }
}
