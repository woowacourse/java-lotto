package domain;

import java.util.Map;

public class Profit {

    public static final int LOTTO_PRICE = 1000;

    public int calculateProfitRatio(Map<LottoResult, Integer> result, int lottoCount) {
        return calculateWinningMoney(result) / (LOTTO_PRICE * lottoCount);
    }

    private int calculateWinningMoney(Map<LottoResult, Integer> result) {
        int profit = 0;
        for (LottoResult rank : result.keySet()) {
            profit += rank.getWinningMoney() * result.get(rank);
        }
        return profit;
    }
}
