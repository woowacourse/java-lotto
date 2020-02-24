package domain;

import java.util.Map;

public class LottoProfit {
    public static final int TO_PERCENTAGE = 100;

    private int profit;

    private LottoProfit(int profit) {
        this.profit = profit;
    }

    public static LottoProfit ofProfit(Map<LottoResult, Integer> lottoResults, Money money) {
        int totalPrize = 0;
        for (LottoResult result : lottoResults.keySet()) {
            int key = result.getPrize();
            int value = (int) (long) lottoResults.get(result);
            totalPrize += key * value;
        }

        return new LottoProfit(totalPrize / money.getMoney() * TO_PERCENTAGE);
    }

    public int getValue() {
        return profit;
    }
}