package lotto.domain;

import java.util.Map;

import static lotto.domain.PurchaseAmount.LOTTO_UNIT_PRICE;

public class WinningStatistics {
    private static final int TRUNCATION = 100;

    private final Map<Rank, Integer> winningStatistics;

    public WinningStatistics(final Map<Rank, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public double calculateReturnRate(final int amount) {
        int total = calculateTotal();
        double returnRate = (double) total / (amount * LOTTO_UNIT_PRICE);
        return Math.floor(returnRate * TRUNCATION) / TRUNCATION;
    }

    private int calculateTotal() {
        int total = 0;
        for (final Rank rank : winningStatistics.keySet()) {
            total += rank.getWinningAmountByCount(winningStatistics.get(rank));
        }
        return total;
    }

    public int getRankCount(final Rank rank) {
        return winningStatistics.getOrDefault(rank, 0);
    }
}
