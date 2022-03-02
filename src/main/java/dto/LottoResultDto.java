package dto;

import domain.Rank;
import java.util.Collections;
import java.util.LongSummaryStatistics;
import java.util.Map;

public class LottoResultDto {
    private final Map<Rank, LongSummaryStatistics> winningCountByRank;
    private final double rateOfReturn;

    private LottoResultDto(Map<Rank, LongSummaryStatistics> winningCountByRank, double rateOfReturn) {
        this.winningCountByRank = Collections.unmodifiableMap(winningCountByRank);
        this.rateOfReturn = rateOfReturn;
    }

    public static LottoResultDto of(Map<Rank, LongSummaryStatistics> winningCountByRank, double profitRatio) {
        return new LottoResultDto(winningCountByRank, profitRatio);
    }

    public LongSummaryStatistics getWinningCountByRank(Rank rank) {
        return winningCountByRank.get(rank);
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }

    @Override
    public String toString() {
        return "LottoResultDto{" +
                "winningCountByRank=" + winningCountByRank +
                ", rateOfReturn=" + rateOfReturn +
                '}';
    }
}
