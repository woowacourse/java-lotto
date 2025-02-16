package lotto;

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
        int total = 0;
        for (final Rank rank : winningStatistics.keySet()) {
            total += rank.calculateAllWinningAmount(winningStatistics.get(rank));
        }
        return total;
    }

    public int getRankCount(final Rank rank) {
        return winningStatistics.get(rank);
    }
}
