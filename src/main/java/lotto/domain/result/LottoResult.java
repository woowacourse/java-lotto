package lotto.domain.result;

import lotto.domain.purchase_info.LottoMoney;

import java.util.Map;

public class LottoResult {

    private static final int HUNDRED_PERCENTAGE = 100;

    private Map<Rank, Integer> rankMap;

    public LottoResult(Map<Rank, Integer> rankMap) {
        this.rankMap = rankMap;
    }

    public int count(Rank rank) {
        return rankMap.get(rank);
    }

    public double getProfit(LottoMoney lottoMoney) {
        double profit = getTotalPrize() - lottoMoney.getMoney();
        double profitRatio = profit / lottoMoney.getMoney();
        return profitRatio * HUNDRED_PERCENTAGE;
    }

    private long getTotalPrize() {
        return rankMap.keySet().stream()
                .mapToLong(rank -> rank.multiply(rankMap.get(rank)))
                .sum();
    }
}
