package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Ranks {
    private static final int DEFAULT_SUM = 0;

    private final List<Rank> ranks;

    private Ranks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public static Ranks valueOf(List<Rank> ranks) {
        Collections.sort(ranks);
        return new Ranks(ranks);
    }

    public double calculateProfit(Money purchaseMoney) {
        return sumWinningMoney().calculatePercentage(purchaseMoney);
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
