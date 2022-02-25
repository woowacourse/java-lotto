package dto;

import domain.Rank;
import java.util.Collections;
import java.util.Map;

public class LottoResultDto {
    private static final long ZERO_FOR_INVALID_KEY = 0L;

    private final Map<Rank, Long> winningCountByRank;
    private final double rateOfReturn;

    private LottoResultDto(Map<Rank, Long> winningCountByRank, double rateOfReturn) {
        this.winningCountByRank = Collections.unmodifiableMap(winningCountByRank);
        this.rateOfReturn = rateOfReturn;
    }

    public static LottoResultDto of(Map<Rank, Long> winningCountByRank, double profitRatio) {
        return new LottoResultDto(winningCountByRank, profitRatio);
    }

    public Long getWinningCountByRank(Rank rank) {
        return winningCountByRank.getOrDefault(rank, ZERO_FOR_INVALID_KEY);
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
