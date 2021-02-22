package lottogame.domain.stats;

import lottogame.domain.Money;
import lottogame.domain.Rank;

import java.util.*;

public class LottoResults {
    private Map<Rank, Integer> result = new HashMap<>();
    private int totalWinningAmount = 0;
    private float profit;

    public LottoResults() {
        for (Rank rank : Rank.values()) {
            if (rank.isNotFound()) {
                continue;
            }
            result.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        if (rank.isNotFound()) {
            return;
        }
        result.put(rank, result.get(rank) + 1);
        totalWinningAmount += rank.getMoney();
    }

    public Map<Rank, Integer> values() {
        return this.result;
    }

    public void calculateProfit(Money money) {
        profit = (float) totalWinningAmount / money.getMoney();
    }

    public float getProfit() {
        return this.profit;
    }
}
