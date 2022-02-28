package model;

import java.util.EnumMap;

public class WinningStatistics {
    private final EnumMap<LottoRank, Integer> winningStatistics;

    public WinningStatistics() {
        winningStatistics = new EnumMap<>(LottoRank.class);
        for (LottoRank each : LottoRank.values()) {
            winningStatistics.put(each, 0);
        }
    }

    public void put(LottoRank lottoRank) {
        winningStatistics.put(lottoRank, winningStatistics.get(lottoRank) + 1);
    }

    public int get(LottoRank lottoRank) {
        return winningStatistics.get(lottoRank);
    }

    public double getEarningsRate(LottoPurchasingMoney lottoPurchasingMoney) {
        long totalPrize = getTotalPrize();
        return totalPrize / (double)lottoPurchasingMoney.getAmount();
    }

    long getTotalPrize() {
        return winningStatistics.keySet().stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * winningStatistics.get(lottoRank))
                .sum();
    }
}
