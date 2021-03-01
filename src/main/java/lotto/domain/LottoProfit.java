package lotto.domain;

import java.util.Map;

public class LottoProfit {
    private final float profit;

    private LottoProfit(float profit) {
        this.profit = profit;
    }

    public static LottoProfit ofProfit(Map<Rank, Integer> results, Money money) {
        float totalWinnings = 0;
        for (Rank result : results.keySet()) {
            float winnings = result.getWinnings();
            float hitCount = results.get(result);
            totalWinnings += winnings * hitCount;
        }
        return new LottoProfit(totalWinnings / money.getMoney());
    }

    public float getProfit() {
        return profit;
    }
}
