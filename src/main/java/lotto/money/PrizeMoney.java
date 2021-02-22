package lotto.money;

import lotto.ranking.Statistics;

import java.util.Objects;

public class PrizeMoney {
    private final int prizeMoney;

    public PrizeMoney(Statistics statistics) {
        this.prizeMoney = statistics.getTotalPrize();
    }

    public String calculateProfit(Money money) {
        return money.calculateProfit(prizeMoney);
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
