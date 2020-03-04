package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Ranks {
    private static final int DEFAULT_SUM = 0;

    private final List<Rank> ranks;

    private Ranks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public static Ranks of(List<Rank> ranks) {
        Collections.sort(ranks);
        return new Ranks(ranks);
    }

    public Profit calculateProfit(Money purchaseMoney) {
        double profitValue = sumWinningMoney().calculatePercentage(purchaseMoney);
        return Profit.of(profitValue);
    }

    private Money sumWinningMoney() {
        Money totalWinningMoney = Money.valueOf(DEFAULT_SUM);
        for (Rank rank : ranks) {
            totalWinningMoney = totalWinningMoney.plus(rank.getWinningMoney());
        }
        return totalWinningMoney;
    }

    public int frequency(Rank rank) {
        return Collections.frequency(ranks, rank);
    }

    public boolean contains(Rank rank) {
        return ranks.contains(rank);
    }
}
