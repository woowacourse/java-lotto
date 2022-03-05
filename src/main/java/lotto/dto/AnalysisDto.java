package lotto.dto;

import java.util.Map;

import lotto.domain.analysis.Analysis;
import lotto.utils.Rank;

public class AnalysisDto {

    private final Map<Rank, Long> rankCounts;
    private final double profitRate;

    public AnalysisDto(final Map<Rank, Long> rankCounts, final double profitRate) {
        this.rankCounts = Map.copyOf(rankCounts);
        this.profitRate = profitRate;
    }

    public static AnalysisDto toDto(final Analysis analysis) {
        return new AnalysisDto(analysis.getRankCounts(), analysis.getProfitRate());
    }

    public Map<Rank, Long> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
