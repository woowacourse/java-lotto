package lotto.dto;

import java.util.Map;

import lotto.domain.analysis.Analysis;
import lotto.domain.winning.Rank;

public class AnalysisDto {

    private final Map<Rank, Integer> rankCounts;
    private final double profitRate;

    public AnalysisDto(final Map<Rank, Integer> rankCounts, final double profitRate) {
        this.rankCounts = rankCounts;
        this.profitRate = profitRate;
    }

    public static AnalysisDto toDto(Analysis analysis) {
        final Map<Rank, Integer> rankCounts = analysis.getRankCounts();
        final double profitRate = analysis.getProfitRate();
        return new AnalysisDto(rankCounts, profitRate);
    }

    public Map<Rank, Integer> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
