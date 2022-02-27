package model.result;

import model.lotto.LottoCount;

public class RateOfReturn {
    private static final int UNIT = 1000;
    private final int money;
    private Profit profit;

    public RateOfReturn(LottoCount lottoCount) {
        this.money = lottoCount.getCount() * UNIT;
        this.profit = new Profit();
    }

    public double calcRateOfReturn(WinningResult winningResult) {
        return (profit.sumProfitMoney(winningResult) / (double) money);
    }
}
