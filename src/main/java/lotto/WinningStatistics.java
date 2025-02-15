package lotto;

import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> winningStatistics;

    public WinningStatistics(final Map<Rank, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public double calculateReturnRate(final int purchaseAmount) {
        int total = calculateTotal();
        return (double) total / purchaseAmount;
    }

    private int calculateTotal() {
        int total = 0;
        for (final Rank rank : winningStatistics.keySet()) {
            total += winningStatistics.get(rank) * rank.getWinningAmount();
        }
        return total;
    }

    public int getRankCount(final Rank rank) {
        return winningStatistics.get(rank);
    }
}
