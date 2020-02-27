package Lotto.domain;

public class EarningRate {
    private double earningRate;

    public EarningRate(Ranks ranks, PurchaseAmount purchaseAmount) {
        int sum = ranks.addAllRankReward();
        int invest = purchaseAmount.getPurchaseAmount();
        this.earningRate = (double)(sum - invest) / invest * 100.0;
    }

    public double getEarningRate() {
        return this.earningRate;
    }
}
