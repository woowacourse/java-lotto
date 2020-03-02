package lotto.domain;

import java.util.Objects;

public class Profit {
    private final double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public static Profit of(double profit) {
        return new Profit(profit);
    }

    public int getProfitWithoutDecimalPoint() {
        return (int) profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit1 = (Profit) o;
        return Double.compare(profit1.profit, profit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }
}