package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCount;
    public static final int INITIAL_PROFIT = 0;
    public static final int LOTTO_PRICE = 1000;

    public LottoResult(Map<LottoRank, Integer> rankCount) {
        this.rankCount = rankCount;
    }

    public int calculateProfitRatio(int lottoCount) {
        return calculateProfit() / (LOTTO_PRICE * lottoCount);
    }

    private int calculateProfit() {
        int profit = INITIAL_PROFIT;
        for (LottoRank rank : rankCount.keySet()) {
            profit += rank.getWinningMoney() * rankCount.get(rank);
        }
        return profit;
    }

    public int getCount(LottoRank rank) {
        return rankCount.get(rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(rankCount, that.rankCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankCount);
    }
}
