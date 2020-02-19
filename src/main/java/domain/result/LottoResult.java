package domain.result;

import java.util.List;

import domain.money.LottoMoney;

public class LottoResult {

    private List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int count(Rank rank) {
        return (int)ranks.stream()
                .filter(has -> has.equals(rank))
                .count();
    }

    public double getProfit(LottoMoney lottoMoney) {
        double profit = getTotalPrize() - lottoMoney.getMoney();
        double profitRatio = profit / lottoMoney.getMoney();
        return profitRatio * 100;
    }

    private double getTotalPrize() {
        return ranks.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();
    }
}
