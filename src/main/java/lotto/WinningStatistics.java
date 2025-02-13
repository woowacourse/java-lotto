package lotto;

import java.util.Map;

public class WinningStatistics {
    private final Map<Prize, Integer> winningStatistics;

    public WinningStatistics(final Map<Prize, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public double calculateReturnRate(final int purchaseAmount) {
        int total = calculateTotal();
        return (double) total / purchaseAmount;
    }

    private int calculateTotal() {
        int total = 0;
        for (final Prize prize : winningStatistics.keySet()) {
            total += winningStatistics.get(prize) * prize.getWinningAmount();
        }
        return total;
    }

    public int getPrizeCount(final Prize prize) {
        return winningStatistics.get(prize);
    }
}
