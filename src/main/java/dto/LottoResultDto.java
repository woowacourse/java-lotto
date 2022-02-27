package dto;

import constants.LottoConstants;
import domain.LottoQuantity;
import domain.Rank;
import domain.WinningCount;
import java.util.Map;

public class LottoResultDto {
    public static final double ROUND_UNIT = 100.0;

    private final Map<Rank, WinningCount> lottoWinningResult;
    private final double profitRatio;

    private LottoResultDto(Map<Rank, WinningCount> lottoWinningResult, double profitRatio) {
        this.lottoWinningResult = lottoWinningResult;
        this.profitRatio = profitRatio;
    }

    public static LottoResultDto from(Map<Rank, WinningCount> lottoWinningResult, LottoQuantity lottoQuantity) {
        Long totalPrize = lottoWinningResult.entrySet()
                .stream()
                .map((entrySet) -> entrySet.getKey().getPrize() * entrySet.getValue().getCount())
                .reduce(0L, Long::sum);

        return new LottoResultDto(lottoWinningResult, getProfitRatio(totalPrize, lottoQuantity));
    }

    private static double getProfitRatio(long totalPrize, LottoQuantity lottoQuantity) {
        double purchaseMoney = lottoQuantity.getLottoQuantity() * LottoConstants.SINGLE_LOTTO_PRICE;
        return roundToSecondDigit(totalPrize / purchaseMoney);
    }

    private static double roundToSecondDigit(double number) {
        return Math.round(number * ROUND_UNIT) / ROUND_UNIT;
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
