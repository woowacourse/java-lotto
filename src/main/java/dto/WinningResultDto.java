package dto;

import domain.LottoQuantity;
import domain.Rank;
import domain.WinningCount;
import domain.WinningResult;
import java.util.Map;

public class WinningResultDto {
    private final Map<Rank, WinningCount> winningResult;
    private final double profitRatio;

    private WinningResultDto(Map<Rank, WinningCount> winningResult, double profitRatio) {
        this.winningResult = winningResult;
        this.profitRatio = profitRatio;
    }

    public static WinningResultDto of(WinningResult winningResult, LottoQuantity lottoQuantity) {
        return new WinningResultDto(
                winningResult.getWinningResult(),
                winningResult.getProfitRatio()
        );
    }

    public WinningCount getWinningCountByRank(Rank rank) {
        return winningResult.get(rank);
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    @Override
    public String toString() {
        return "WinningResultDto{" +
                "winningResult=" + winningResult +
                ", profitRatio=" + profitRatio +
                '}';
    }
}
