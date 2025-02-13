package lotto;

import java.util.Map;

public class WinningStatistics {
    private final Map<Prize, Integer> winningStatistics;

    public WinningStatistics(Map<Prize, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public double calculateReturnRate(int purchaseAmount) {
        int total = calculateTotal();
        return (double) total / purchaseAmount;
    }

    private int calculateTotal() {
        int total = 0;
        for (Prize prize : winningStatistics.keySet()) {
            total += winningStatistics.get(prize) * prize.getWinningAmount();
        }
        return total;
    }

    public int getPrizeCount(Prize prize) {
        return winningStatistics.get(prize);
    }
}
