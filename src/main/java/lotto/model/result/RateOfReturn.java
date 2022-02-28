package lotto.model.result;

import lotto.utils.ConverterUtils;

public class RateOfReturn {
    private final Money money;
    private final Profit profit;

    public RateOfReturn(String money) {
        this.money = new Money(money);
        this.profit = new Profit();
    }

    public double calcRateOfReturn(WinningResult winningResult) {
        return (profit.sumProfitMoney(winningResult) / (double) ConverterUtils.convertStringToInt(money.getNumber()));
    }
}
