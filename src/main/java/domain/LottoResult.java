package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final Map<LottoRank, Integer> result = new HashMap<>();
    private static final int INITIAL_PROFIT = 0;
    private static final int RESULT_INCREMENT = 1;
    private static final int INITIAL_RESULT = 0;

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, INITIAL_RESULT);
        }
    }

    public static void addWinningRankCount(final LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + RESULT_INCREMENT);
        }
    }

    public static int calculateTotalProfit() {
        int profit = INITIAL_PROFIT;
        for (LottoRank rank : result.keySet()) {
            profit += rank.getWinningMoney() * result.get(rank);
        }
        return profit;
    }

    public static int getSize() {
        return result.size();
    }

    public static int getRankCount(final LottoRank rank) {
        return result.get(rank);
    }
}
