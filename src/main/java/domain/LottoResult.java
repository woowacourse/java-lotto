package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final Map<LottoRank, Integer> result = new HashMap<>();
    public static final int INITIAL_PROFIT = 0;

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
    }

    public void addWinningRankCount(final LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + 1);
        }
    }

    public int calculateProfit() {
        int profit = INITIAL_PROFIT;
        for (LottoRank rank : result.keySet()) {
            profit += rank.getWinningMoney() * result.get(rank);
        }
        return profit;
    }

    public int getSize() {
        return result.size();
    }

    public int getCount(final LottoRank rank) {
        return result.get(rank);
    }
}
