package lotto.domain;

import lotto.Exception.IllegalPurchaseAmount;
import lotto.view.OutputView;

import java.util.List;

public class EarningRate {
    public static final int RATE_NUMBER = 100;

    private double earningRate;
    private double totalWinningMoney = 0.0;

    public EarningRate(List<WinningRank> winningRanks, PurchaseAmount purchaseAmount) {
        generateWinningRank(winningRanks);
        if (Double.isInfinite(calculateEarningRate(purchaseAmount))
                || Double.isNaN(calculateEarningRate(purchaseAmount))) {
            throw new IllegalPurchaseAmount("계산 오류입니다.Nan 또는 Infinite 발생");
        }
        this.earningRate = calculateEarningRate(purchaseAmount);
    }

    private int calculateEarningRate(PurchaseAmount purchaseAmount) {
        return (int) (totalWinningMoney / purchaseAmount.getPurchaseAmount() * RATE_NUMBER);
    }

    private void generateWinningRank(List<WinningRank> winningRanks) {
        int count;

        for (WinningRank winningRank : WinningRank.values()) {
            count = countRankPeople(winningRanks, winningRank);

            sumWinningMoney(winningRank.getWinningMoney() * count);
        }
    }

    public int countRankPeople(List<WinningRank> winningRanks, WinningRank winningRank) {
        return (int) winningRanks.stream().filter(Rank -> winningRank == Rank).count();
    }

    private void sumWinningMoney(double totalWinningMoney) {
        this.totalWinningMoney += totalWinningMoney;
    }

    public double getEarningRate() {
        return earningRate;
    }
}