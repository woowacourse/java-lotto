package domain;

import java.util.List;

public class Yield {

    private final double yield;

    private Yield(double yield) {
        this.yield = yield;
    }

    public static Yield calculate(int purchaseAmount, List<WinningCounter> winningCounters) {
        int totalPrizeMoney = winningCounters.stream()
                .mapToInt(winningCounter ->
                        winningCounter.getCount() * winningCounter.getWinningStatistics().getPrizeMoney())
                .sum();
        return new Yield((double) totalPrizeMoney / purchaseAmount);
    }

    public double getYield() {
        return yield;
    }
}
