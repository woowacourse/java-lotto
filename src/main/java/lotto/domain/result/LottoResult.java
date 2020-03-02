package lotto.domain.result;

import java.util.Collections;
import java.util.Map;

import lotto.domain.money.LottoMoney;

public class LottoResult {

    private static final int HUNDRED_PERCENTAGE = 100;

    private Map<Rank, Integer> rankToCount;

    public LottoResult(Map<Rank, Integer> rankToCount) {
        this.rankToCount = Collections.unmodifiableMap(rankToCount);
    }

    public int count(Rank rank) {
        return rankToCount.get(rank);
    }

    public double getProfit(LottoMoney lottoMoney) {
        double profit = getTotalPrize() - lottoMoney.getMoney();
        double profitRatio = profit / lottoMoney.getMoney();
        return profitRatio * HUNDRED_PERCENTAGE;
    }

    private double getTotalPrize() {
        return rankToCount.keySet()
                .stream()
                .mapToLong(rank -> rank.getWinningMoneyByCount(rankToCount.get(rank)))
                .sum();
    }
}
