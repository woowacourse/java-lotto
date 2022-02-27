package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class WinningStats {

    public static final int EARNING_RATE_PRECISIONS = 2;
    private final EnumMap<LottoRank, Integer> winningStatistics;

    public WinningStats() {
        winningStatistics = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> winningStatistics.put(lottoRank, 0));
    }

    public void put(LottoRank lottoRank) {
        winningStatistics.put(lottoRank, winningStatistics.get(lottoRank) + 1);
    }

    public int get(LottoRank lottoRank) {
        return winningStatistics.get(lottoRank);
    }

    public double getEarningsRate(PurchaseAmount money) {
        BigDecimal totalPrize = new BigDecimal(getTotalPrize());
        BigDecimal amount = new BigDecimal(money.amount());
        return totalPrize.divide(amount, EARNING_RATE_PRECISIONS, RoundingMode.HALF_EVEN).doubleValue();
    }

    long getTotalPrize() {
        long totalPrize = 0;
        for (LottoRank lottoRank : winningStatistics.keySet()) {
            totalPrize += lottoRank.prizeMoney() * winningStatistics.get(lottoRank);
        }

        winningStatistics.entrySet().stream()
                .mapToLong(
                        (Map.Entry<LottoRank, Integer> winingStat) ->
                                winingStat.getKey().prizeMoney() * winingStat.getValue()
                ).sum();

        return totalPrize;
    }
}
