package lotto.domain;

public class Profit {

    private Money profit;

    public Profit(Money profit) {
        this.profit = profit;
    }

    public double calculateProfitRate(Money purchaseAmount) {
        return (double)profit.getMoney() / purchaseAmount.getMoney();
    }
}
