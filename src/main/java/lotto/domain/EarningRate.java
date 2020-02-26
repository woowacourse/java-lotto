package lotto.domain;

import java.util.List;

public class EarningRate {
    private static final int RATE_NUMBER = 100;
    private static final String ILLEGAL_PURCHASE_AMOUNT = "계산 오류입니다.Nan 또는 Infinite 발생";

    private double earningRate;
    private double totalWinningMoney = 0.0;

    public EarningRate(List<WinningRank> winningRanks, Money money) {
        generateWinningRank(winningRanks);
        if (Double.isInfinite(calculateEarningRate(money))
                || Double.isNaN(calculateEarningRate(money))) {
            throw new IllegalArgumentException(ILLEGAL_PURCHASE_AMOUNT);
        }
        this.earningRate = calculateEarningRate(money);
    }

    public long countRankPeople(List<WinningRank> winningRanks, WinningRank winningRank) {
        return winningRanks.stream().filter(rank -> winningRank == rank).count();
    }

    public double getEarningRate() {
        return earningRate;
    }

    private double calculateEarningRate(Money money) {
        return ((totalWinningMoney / money.getMoney()) * RATE_NUMBER);
    }

    private void sumWinningMoney(double totalWinningMoney) {
        this.totalWinningMoney += totalWinningMoney;
    }

    private void generateWinningRank(List<WinningRank> winningRanks) {
        long count;

        for (WinningRank winningRank : WinningRank.values()) {
            count = countRankPeople(winningRanks, winningRank);

            sumWinningMoney(winningRank.getWinningMoney() * count);
        }
    }
}