package lotto.domain;

import java.util.List;

public class Profit {
    private final double profit;

    public Profit(Money purchaseMoney, List<Rank> ranks) {
        Money totalWinningMoney = Rank.sumWinningMoney(ranks);
        this.profit = totalWinningMoney.calculatePercentage(purchaseMoney);
    }

    public int getProfitWithoutDecimalPoint() {
        return (int) profit;
    }
}