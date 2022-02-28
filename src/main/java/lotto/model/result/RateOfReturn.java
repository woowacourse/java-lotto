package lotto.model.result;

public class RateOfReturn {
    private final Money money;
    private final Profit profit;

    public RateOfReturn(String money) {
        this.money = new Money(Integer.parseInt(money));
        this.profit = new Profit();
    }

    public double calcRateOfReturn(WinningResult winningResult) {
        return (profit.sumProfitMoney(winningResult) / (double) money.getNumber());
    }
}
