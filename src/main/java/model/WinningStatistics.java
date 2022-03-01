package model;

import java.util.EnumMap;
import java.util.Map;

public class WinningStatistics {
    private final EnumMap<LottoRank, Integer> winningCounts;

    public WinningStatistics() {
        winningCounts = new EnumMap<>(LottoRank.class);
        for (LottoRank each : LottoRank.values()) {
            winningCounts.put(each, 0);
        }
    }

    public void put(LottoRank lottoRank) {
        winningCounts.put(lottoRank, winningCounts.get(lottoRank) + 1);
    }

    public int get(LottoRank lottoRank) {
        return winningCounts.get(lottoRank);
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return winningCounts;
    }

    public double getEarningsRate(LottoPurchasingMoney lottoPurchasingMoney) {
        long totalPrize = getTotalPrize();
        return totalPrize / (double)lottoPurchasingMoney.getAmount();
    }

    long getTotalPrize() {
        return winningCounts.keySet().stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * winningCounts.get(lottoRank))
                .sum();
    }
}
