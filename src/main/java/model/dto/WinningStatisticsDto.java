package model.dto;

import java.util.Map;
import model.LottoPurchasingMoney;
import model.LottoRank;
import model.WinningStatistics;

public class WinningStatisticsDto {
    private final Map<LottoRank, Integer> winningCounts;
    private final double earningsRate;

    public WinningStatisticsDto(WinningStatistics winningStatistics, LottoPurchasingMoney purchasingMoney) {
        this.winningCounts = Map.copyOf(winningStatistics.getWinningCounts());
        this.earningsRate = winningStatistics.getEarningsRate(purchasingMoney);
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return winningCounts;
    }

    public double getEarningsRate() {
        return earningsRate;
    }
}
