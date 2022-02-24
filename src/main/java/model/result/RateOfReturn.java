package model.result;

import java.util.Arrays;

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
}
