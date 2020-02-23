package domain;

import java.util.Map;

public class LottoProfit {
    public static final int TO_PERCENTAGE = 100;
    private double profit;

    private LottoProfit(double profit) {
        this.profit = profit;
    }

    public static LottoProfit getProfit(Map<LottoResult, Long> lottoResults, Money money) {
        Long totalPrize = lottoResults.keySet().stream()
                .mapToLong(object -> object.getPrize() * lottoResults.get(object))
                .sum();

        return new LottoProfit(totalPrize / money.getMoney() * TO_PERCENTAGE);
    }

    public double getValue() {
        return profit;
    }
}
