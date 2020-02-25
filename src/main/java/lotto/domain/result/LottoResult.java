package lotto.domain.result;

import lotto.domain.money.LottoMoney;

import java.util.List;

public class LottoResult {

    private static final int HUNDRED_PERCENTAGE = 100;

    private List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int count(Rank rank) {
        return (int) ranks.stream()
                .filter(has -> has.equals(rank))
                .count();
    }

    public double getProfit(LottoMoney lottoMoney) {
        double profit = getTotalPrize() - lottoMoney.getMoney();
        double profitRatio = profit / lottoMoney.getMoney();
        return profitRatio * HUNDRED_PERCENTAGE;
    }

    private double getTotalPrize() {
        return ranks.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();
    }
}
