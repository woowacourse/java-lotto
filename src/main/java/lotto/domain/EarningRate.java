package lotto.domain;

public class EarningRate {
    private int earningRate;
    private long totalWinningMoney = 0;

    public void sumWinningMoney(int totalWinningMoney) {
        this.totalWinningMoney += totalWinningMoney;
    }

    public void calculateEarningRate(PurchaseAmount purchaseAmount) {
        earningRate = (int) (totalWinningMoney / purchaseAmount.getPurchaseAmount());
    }

    public long getEarningRate() {
        return earningRate;
    }
}