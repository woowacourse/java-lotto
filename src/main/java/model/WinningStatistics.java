package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {
    private final EnumMap<LottoRank, Integer> winningCounts = new EnumMap<LottoRank, Integer>(LottoRank.class);
    private final double earningsRate;

    public WinningStatistics(List<Lotto> lotteries, WinningLotto winningLotto, LottoPurchasingMoney lottoPurchasingMoney) {
        for (LottoRank each : LottoRank.values()) {
            winningCounts.put(each, 0);
        }
        countWinningLotto(lotteries, winningLotto);
        this.earningsRate = calculateEarningsRate(lottoPurchasingMoney);
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return winningCounts;
    }

    public double getEarningsRate() {
        return earningsRate;
    }

    private void countWinningLotto(List<Lotto> lotteries, WinningLotto winningLotto) {
        lotteries.forEach(lotto -> {
            LottoRank lottoRank = LottoRank.getRank(
                    winningLotto.countMatching(lotto),
                    winningLotto.containBonusBall(lotto)
            );
            winningCounts.put(lottoRank, winningCounts.get(lottoRank) + 1);
        });
    }

    private double calculateEarningsRate(LottoPurchasingMoney lottoPurchasingMoney) {
        long totalPrize = getTotalPrize();
        return totalPrize / (double)lottoPurchasingMoney.getAmount();
    }

    private long getTotalPrize() {
        return winningCounts.keySet().stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * winningCounts.get(lottoRank))
                .sum();
    }
}
