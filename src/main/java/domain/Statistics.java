package domain;

import java.util.Map;

public class Statistics {
    private Map<Rank, CountOfRank> countsOfRanks;
    private PurchaseAmount purchasedAmount;

    private Statistics(Map<Rank, CountOfRank> countsOfRanks, PurchaseAmount purchaseAmount) {
        this.countsOfRanks = countsOfRanks;
        this.purchasedAmount = purchaseAmount;
    }

    static Statistics of(Map<Rank, CountOfRank> countsOfRanks, PurchaseAmount purchaseAmount) {
        return new Statistics(countsOfRanks, purchaseAmount);
    }

    public int countsOf(Rank rank) {
        CountOfRank countOfRank = countsOfRanks.get(rank);

        return countOfRank.getCount();
    }

    public double calculateEarningRates() {
        int winningMoneyOfRank;
        int totalWinningMoney = 0;

        for (Rank rank : countsOfRanks.keySet()) {
            winningMoneyOfRank = rank.getWinningMoney();
            totalWinningMoney += countsOf(rank) * winningMoneyOfRank;
        }
        return totalWinningMoney / (double) purchasedAmount.getMoneyAmount();
    }
}
