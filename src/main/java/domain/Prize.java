package domain;

import java.util.SortedMap;

public class Prize {

    private static final int INIT_WIN_PRIZE = 0;

    private final int prize;

    private Prize(final int prize) {
        this.prize = prize;
    }

    public static Prize getPrize(int prize) {
        return new Prize(prize);
    }

    public int getPrize() {
        return this.prize;
    }

    public static Prize calculatePrize(SortedMap<RankPrize, Integer> rankCounts) {
        int totalPrize = INIT_WIN_PRIZE;
        for (RankPrize rankPrize : rankCounts.keySet()) {
            totalPrize += rankPrize.getPrize() * rankCounts.get(rankPrize);
        }
        return new Prize(totalPrize);
    }

    public double calculateProfit(int counts) {
        return (double) prize / counts;
    }
}
