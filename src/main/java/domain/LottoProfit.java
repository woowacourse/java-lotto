package domain;

import java.util.Map;

public class LottoProfit {
    private double profit;

    public LottoProfit(double profit) {
        this.profit = profit;
    }

    public static LottoProfit getProfit(Map<LottoResult, Long> lottoResults, Money money) {
        Long totalPrize = lottoResults.keySet().stream()
                .mapToLong(object -> object.getPrize() * lottoResults.get(object))
                .sum();

        return new LottoProfit(totalPrize * 100 / money.getMoney());
    }

    public double getProfit() {
        return profit;
    }
}
