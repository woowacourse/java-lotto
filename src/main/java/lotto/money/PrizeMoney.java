package lotto.money;

import lotto.ranking.Ranking;
import lotto.ranking.Statistics;

import java.util.Objects;

public class PrizeMoney {
    private final int prizeMoney;

    public PrizeMoney(Statistics statistics) {
        this.prizeMoney = totalPrize(statistics);
    }

    private int totalPrize(Statistics statistics) {
        int prize = 0;
        for (Ranking ranking : Ranking.values()) {
            prize += ranking.calculatePrize(statistics.findRankingCount(ranking));
        }
        return prize;
    }

    public Double calculateProfit(Money money) {
        return money.divideByMoney(prizeMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeMoney that = (PrizeMoney) o;
        return prizeMoney == that.prizeMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeMoney);
    }
}
