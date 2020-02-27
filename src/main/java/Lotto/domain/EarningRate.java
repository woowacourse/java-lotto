package Lotto.domain;

public class EarningRate {
    private double earningRate;
    private int profit;

    public EarningRate(Ranks ranks, PurchaseAmount purchaseAmount) {
        this.profit = ranks.addAllRankReward();
        int invest = purchaseAmount.getPurchaseAmount();
        this.earningRate = (double)(this.profit) / invest * 100.0;
    }

    public double getEarningRate() {
        return this.earningRate;
    }

    public int getProfitAmount() { return this.profit; }
}
