package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final Map<LottoRank, Integer> result = new HashMap<>();
    public static final int INITIAL_PROFIT = 0;
    public static final int RESULT_INCREMENT = 1;
    public static final int INITIAL_RESULT = 0;

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, INITIAL_RESULT);
        }
    }

    public void addWinningRankCount(final LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + RESULT_INCREMENT);
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
