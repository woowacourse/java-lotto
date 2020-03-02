package domain;

import java.util.Map;

public class LottoProfit {
    private static final int TO_PERCENTAGE = 100;

    private long profit;

    private LottoProfit(long profit) {
        this.profit = profit;
    }

    public static LottoProfit ofProfit(LottoResult lottoResult, Money money) {
        long totalPrize = 0;
        for (LottoRank rank : lottoResult.keySet()) {
            long prize = rank.getPrize();
            long matchCount = lottoResult.count(rank);
            totalPrize += prize * matchCount;
        }
        return new LottoProfit(totalPrize / money.getValue() * TO_PERCENTAGE);
    }

    public long getValue() {
        return profit;
    }
}