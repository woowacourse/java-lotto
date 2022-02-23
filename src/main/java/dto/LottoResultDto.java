package dto;

import domain.Rank;
import domain.TrialNumber;
import domain.WinningCount;
import java.util.Map;
import java.util.Objects;

public class LottoResultDto {
    private final Map<Rank, WinningCount> lottoWinningResult;
    private final double profitRatio;

    private LottoResultDto(Map<Rank, WinningCount> lottoWinningResult, double profitRatio) {
        this.lottoWinningResult = lottoWinningResult;
        this.profitRatio = profitRatio;
    }

    public static LottoResultDto from(Map<Rank, WinningCount> lottoWinningResult, TrialNumber trialNumber) {
        Long totalPrize = lottoWinningResult.entrySet()
                .stream()
                .map((entrySet) -> entrySet.getKey().getPrize() * entrySet.getValue().getCount())
                .reduce(0L, Long::sum);

        return new LottoResultDto(lottoWinningResult, getProfitRatio(totalPrize, trialNumber));
    }

    private static double getProfitRatio(long totalPrize, TrialNumber trialNumber) {
        return Math.round(totalPrize / (trialNumber.getTrialNumber() * 1000.0) * 100) / 100.0;
    }

    public WinningCount getWinningCountByRank(Rank rank) {
        return lottoWinningResult.get(rank);
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoResultDto that = (LottoResultDto) object;
        return Double.compare(that.profitRatio, profitRatio) == 0 && Objects.equals(lottoWinningResult,
                that.lottoWinningResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoWinningResult, profitRatio);
    }

    @Override
    public String toString() {
        return "LottoResultDto{" +
                "lottoWinningResult=" + lottoWinningResult +
                ", profitRatio=" + profitRatio +
                '}';
    }
}
