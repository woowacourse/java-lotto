package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinPrize {
    private Map<Rank, Integer> ranks;

    public WinPrize() {
        ranks = new HashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }

    public void addWinCount(final Rank rank) {
        ranks.put(rank, ranks.get(rank) + 1);
    }

    public Integer getWinCount(Rank rank) {
        return ranks.get(rank);
    }

    public long getRateOfProfit(long purchasedAmount) {
        return getTotalPrize() / purchasedAmount * 100;
    }

    private long getTotalPrize() {
        long totalPrize = 0;
        for (Rank rank : ranks.keySet()) {
            totalPrize += rank.getPrize() * ranks.get(rank);
        }
        return totalPrize;
    }
}
