package model.dto;

import java.util.Map;
import model.LottoRank;

public class WinningStatisticsDto {
    private final Map<LottoRank, Integer> winningCounts;
    private final double earningsRate;

    public WinningStatisticsDto(Map<LottoRank, Integer> winningCounts, double earningsRate) {
        this.winningCounts = Map.copyOf(winningCounts);
        this.earningsRate = earningsRate;
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return winningCounts;
    }

    public double getEarningsRate() {
        return earningsRate;
    }
}
