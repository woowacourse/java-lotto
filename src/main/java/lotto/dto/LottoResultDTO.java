package lotto.dto;

import lotto.domain.lottoresult.RankCount;

import java.math.BigDecimal;
import java.util.List;

public class LottoResultDTO {
    private List<RankCount> rankCounts;
    private BigDecimal winningReward;
    private BigDecimal earningRate;

    public List<RankCount> getRankCounts() {
        return rankCounts;
    }

    public void setRankCounts(List<RankCount> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public BigDecimal getWinningReward() {
        return winningReward;
    }

    public void setWinningReward(BigDecimal winningReward) {
        this.winningReward = winningReward;
    }

    public BigDecimal getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(BigDecimal earningRate) {
        this.earningRate = earningRate;
    }
}
