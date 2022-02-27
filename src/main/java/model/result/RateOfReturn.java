package model.result;

import model.lotto.LottoCount;

public class RateOfReturn {
    private final Money money;
    private final Profit profit;

    public RateOfReturn(LottoCount lottoCount) {
        this.money = new Money(lottoCount.getCount());
        this.profit = new Profit();
    }

    public double calcRateOfReturn(WinningResult winningResult) {
        return (profit.sumProfitMoney(winningResult) / (double) money.getNumber());
    }
}
