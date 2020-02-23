package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result = new HashMap<>();
    public static final int INITIAL_PROFIT = 0;
    public static final int LOTTO_PRICE = 1000;

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
    }

    public void addWinningRankCount(LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + 1);
        }
    }

    public int calculateProfitRatio(int lottoCount) {
        return calculateProfit() / (LOTTO_PRICE * lottoCount);
    }

    private int calculateProfit() {
        int profit = INITIAL_PROFIT;
        for (LottoRank rank : result.keySet()) {
            profit += rank.getWinningMoney() * result.get(rank);
        }
        return profit;
    }

    public int getSize() {
        return result.size();
    }

    public int getCount(LottoRank rank) {
        return result.get(rank);
    }
}
