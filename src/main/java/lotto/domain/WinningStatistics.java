package lotto.domain;

import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> winningStatistics;

    public WinningStatistics(final Map<Rank, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public double calculateReturnRate(final int purchaseAmount) {
        int totalWinningAmount = calculateTotalWinningAmount();
        return (double) totalWinningAmount / purchaseAmount;
    }

    private int calculateTotalWinningAmount() {
        return winningStatistics.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().calculateAllWinningAmount(entry.getValue()))
                .sum();
    }

    public int getRankCount(final Rank rank) {
        return winningStatistics.get(rank);
    }
}
