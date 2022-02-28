package model;

import java.util.Arrays;
import java.util.EnumMap;

public class WinningStatistics {
    private final EnumMap<LottoRank, Integer> winningStatistics;

    public WinningStatistics() {
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
