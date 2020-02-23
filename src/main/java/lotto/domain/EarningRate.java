package lotto.domain;

import lotto.view.OutputView;

import java.util.List;

public class EarningRate {
    public static final int RATE_NUMBER = 100;

    private long earningRate;
    private long totalWinningMoney = 0;

    public EarningRate(List<WinningRank> winningRanks, PurchaseAmount purchaseAmount) {
        for (WinningRank winningRank : WinningRank.values()) {
            int count = (int) winningRanks.stream()
                    .filter(Rank -> winningRank == Rank)
                    .count();
            OutputView.printWinningResult(winningRank, count);
            sumWinningMoney(winningRank.getWinningMoney() * count);
        }
        this.earningRate = totalWinningMoney / purchaseAmount.getPurchaseAmount() * RATE_NUMBER;
    }

    private void sumWinningMoney(int totalWinningMoney) {
        this.totalWinningMoney += totalWinningMoney;
    }

    public long getEarningRate() {
        return earningRate;
    }
}