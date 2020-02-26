package lotto.domain.result;

import lotto.domain.money.LottoMoney;

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

    private double getTotalPrize() {
        long totalPrize = 0;

        for (Rank rank : rankMap.keySet()) {
            totalPrize += rank.getWinningMoney() * rankMap.get(rank);
        }

        return totalPrize;
    }
}
