package lotto.domain;

import java.util.Map;

public class WinningResult {

    private final Map<LottoRank, Integer> winningInfo;

    public WinningResult(Map<LottoRank, Integer> winningInfo) {
        this.winningInfo = winningInfo;
    }

    public double calculateProfitRate(final PurchaseAmount purchaseAmount) {
        long totalProfit = sumTotalProfit(winningInfo);
        return (double) totalProfit / purchaseAmount.getAmount();
    }

    private long sumTotalProfit(final Map<LottoRank, Integer> winningInfo) {
        return winningInfo.entrySet().stream()
                .mapToLong(entry ->
                        entry.getKey().calculateWinningAmount(entry.getValue()))
                .sum();
    }
}
