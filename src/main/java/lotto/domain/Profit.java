package lotto.domain;

public class Profit {

    private int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double) profit / purchaseAmount.getPurchaseAmount();
    }
}
