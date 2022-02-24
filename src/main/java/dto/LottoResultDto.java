package dto;

import domain.Rank;
import java.util.Map;
import vo.TrialNumber;
import vo.WinningCount;

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
    public String toString() {
        return "LottoResultDto{" +
                "lottoWinningResult=" + lottoWinningResult +
                ", profitRatio=" + profitRatio +
                '}';
    }
}
