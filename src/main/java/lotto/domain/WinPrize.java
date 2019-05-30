package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinPrize {
    private static final int INIT_COUNT = 0;
    private static final int PERCENT = 100;
    private static final int COUNT = 1;

    private Map<Rank, Integer> ranks;

    public WinPrize() {
        ranks = new HashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, INIT_COUNT);
        }
    }

    public void addWinCount(final Rank rank) {
        ranks.put(rank, ranks.get(rank) + COUNT);
    }

    public Integer getWinCount(Rank rank) {
        return ranks.get(rank);
    }

    public double getRateOfProfit(long purchasedAmount) {
        return getTotalPrize() / purchasedAmount * PERCENT;
    }

    private double getTotalPrize() {
        double totalPrize = 0;
        for (Rank rank : ranks.keySet()) {
            totalPrize += rank.getPrize() * ranks.get(rank);
        }
        System.out.println(totalPrize+"원");
        return totalPrize;
    }
}
