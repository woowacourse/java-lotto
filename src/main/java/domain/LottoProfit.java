package domain;

import java.util.Map;

public class LottoProfit {
    private static final int TO_PERCENTAGE = 100;

    private long profit;

    private LottoProfit(long profit) {
        this.profit = profit;
    }

    public static LottoProfit of(LottoResult lottoResult, Money money) {
        return new LottoProfit(lottoResult.getTotalPrize() / money.getValue() * TO_PERCENTAGE);
    }

    public long getValue() {
        return profit;
    }
}