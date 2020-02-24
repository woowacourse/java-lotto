package lotto.domain;

public class Result {

    private WinningRanks winningRanks;
    private Money totalWinningMoney;
    private int earningRate;

    Result(WinningRanks winningRanks, Money purchaseAmount) {
        this.winningRanks = winningRanks;
        totalWinningMoney = winningRanks.calculateTotalWinningMoney();
        earningRate = totalWinningMoney.toEarningRate(purchaseAmount);
    }

    public WinningRanks getWinningRanks() {
        return winningRanks;
    }

    public int getEarningRate() {
        return earningRate;
    }
}
