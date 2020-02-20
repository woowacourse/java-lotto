package domain;

import java.util.Map;

public class Profit {

    public static final int LOTTO_PRICE = 1000;

    public int calculateProfitRatio(Map<LottoRank, Integer> result, int lottoCount) {
        return calculateWinningMoney(result) / (LOTTO_PRICE * lottoCount);
    }

    private int calculateWinningMoney(Map<LottoRank, Integer> result) {
        int profit = 0;
        for (LottoRank rank : result.keySet()) {
            profit += rank.getWinningMoney() * result.get(rank);
        }
        return profit;
    }
}
