package domain;

import java.util.Map;

public class LottoProfit {

    private double profit;

    public LottoProfit(double profit) {
        this.profit = profit;
    }

    public static LottoProfit getProfit(Map<LottoResult, Long> lottoResults, Money money) {
        double totalPrize = lottoResults.keySet().stream()
                .filter(object -> lottoResults.get(object) != null)
                .mapToDouble(object -> object.getPrize() * lottoResults.get(object))
                .sum();
        return new LottoProfit(totalPrize / money.getMoney() * 100);
    }

    public double getProfit() {
        return profit;
    }
}
