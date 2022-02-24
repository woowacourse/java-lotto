package model.result;

import java.util.Arrays;
import java.util.Objects;

import model.lotto.LottoCount;

public class RateOfReturn {
    private static final int UNIT = 1000;
    private final int money;
    private int sumMoneyOfReturns;

    public RateOfReturn(LottoCount lottoCount) {
        this.money = lottoCount.getCount() * UNIT;
        this.sumMoneyOfReturns = 0;
    }

    public double getRateOfReturn() {
        Arrays.stream(Statistics.values())
                .forEach(statistics -> sumMoneyOfReturns += (statistics.getCount() * statistics.getValue()));

        return (sumMoneyOfReturns / (double) money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateOfReturn that = (RateOfReturn) o;
        return money == that.money && sumMoneyOfReturns == that.sumMoneyOfReturns;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, sumMoneyOfReturns);
    }
}
