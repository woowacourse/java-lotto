package lotto.domain;

import lotto.view.OutputView;

import java.util.List;

public class EarningRate {
    public static final int RATE_NUMBER = 100;

    private int earningRate;
    private long totalWinningMoney = 0;

    public EarningRate(List<WinningRank> winningRanks, PurchaseAmount purchaseAmount) {
        generateWinningRank(winningRanks);
        this.earningRate = (int) (totalWinningMoney / purchaseAmount.getPurchaseAmount() * RATE_NUMBER);
    }

    private void generateWinningRank(List<WinningRank> winningRanks) {
        for (WinningRank winningRank : WinningRank.values()) {
            int count = countRankPeople(winningRanks, winningRank);

            sumWinningMoney(winningRank.getWinningMoney() * count);
        }
    }

    public int countRankPeople(List<WinningRank> winningRanks, WinningRank winningRank) {
        return (int) winningRanks.stream().filter(Rank -> winningRank == Rank).count();
    }

    private void sumWinningMoney(int totalWinningMoney) {
        this.totalWinningMoney += totalWinningMoney;
    }

    public long getEarningRate() {
        return earningRate;
    }
}