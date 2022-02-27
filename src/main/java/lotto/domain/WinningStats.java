package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class WinningStats {

    public static final int EARNING_RATE_PRECISIONS = 2;
    private final EnumMap<LottoRank, Integer> winningStats;

    public WinningStats() {
        winningStats = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> winningStats.put(lottoRank, 0));
    }

    public void put(LottoRank lottoRank) {
        winningStats.put(lottoRank, winningStats.get(lottoRank) + 1);
    }

    public int get(LottoRank lottoRank) {
        return winningStats.get(lottoRank);
    }

    public double getEarningsRate(PurchaseAmount money) {
        BigDecimal totalPrize = new BigDecimal(getTotalPrize());
        BigDecimal amount = new BigDecimal(money.amount());
        return totalPrize.divide(amount, EARNING_RATE_PRECISIONS, RoundingMode.HALF_EVEN).doubleValue();
    }

    long getTotalPrize() {
        return winningStats.entrySet().stream()
                .mapToLong(
                        (Map.Entry<LottoRank, Integer> winingStat) ->
                                winingStat.getKey().prizeMoney() * winingStat.getValue()
                ).sum();
    }
}
