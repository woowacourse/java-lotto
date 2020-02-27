package domain;

import java.util.Map;

public class LottoProfit {
    private static final int TO_PERCENTAGE = 100;

    private long profit;

    private LottoProfit(long profit) {
        this.profit = profit;
    }

    public static LottoProfit ofProfit(Map<LottoResult, Integer> lottoResults, Money money) {
        long totalPrize = 0;
        for (LottoResult result : lottoResults.keySet()) {
            long prize = result.getPrize();
            long matchCount = lottoResults.get(result);
            totalPrize += prize * matchCount;
        }
        return new LottoProfit(totalPrize / money.getValue() * TO_PERCENTAGE);
    }

    public long getValue() {
        return profit;
    }
}