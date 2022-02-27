package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;

public class WinningStats {

    public static final int EARNING_RATE_PRECISIONS = 2;
    private final EnumMap<LottoRank, Integer> lottoRankMap;

    public WinningStats() {
        lottoRankMap = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoRankMap.put(lottoRank, 0));
    }

    public void put(LottoRank lottoRank) {
        lottoRankMap.put(lottoRank, lottoRankMap.get(lottoRank) + 1);
    }

    public int get(LottoRank lottoRank) {
        return lottoRankMap.get(lottoRank);
    }

    public double getEarningsRate(PurchaseAmount money) {
        BigDecimal totalPrize = new BigDecimal(getTotalPrize());
        BigDecimal amount = new BigDecimal(money.amount());
        return totalPrize.divide(amount, EARNING_RATE_PRECISIONS, RoundingMode.HALF_EVEN).doubleValue();
    }

    long getTotalPrize() {
        return lottoRankMap.keySet().stream()
                .mapToLong((LottoRank lottoRank) ->
                        lottoRank.prizeMoney() * lottoRankMap.get(lottoRank))
                .sum();
    }
}
