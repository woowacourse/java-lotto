package domain;

import domain.money.Money;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<Rank, CountOfRank> countsOfRanks;
    private Money purchasedAmount;

    private Statistics(Map<Rank, CountOfRank> countsOfRanks, Money purchasedAmount) {
        this.countsOfRanks = new EnumMap<>(countsOfRanks);
        this.purchasedAmount = Money.amountOf(purchasedAmount.getAmount());
    }

    static Statistics of(Map<Rank, CountOfRank> countsOfRanks, Money purchasedAmount) {
        return new Statistics(countsOfRanks, purchasedAmount);
    }

    public int countsOf(Rank rank) {
        CountOfRank countOfRank = countsOfRanks.get(rank);

        return countOfRank.getCount();
    }

    public double calculateEarningRates() {
        int amountOfTotalWinningMoney = 0;

        for (Rank rank : countsOfRanks.keySet()) {
            Money winningMoneyOfRank = rank.getWinningMoney();
            amountOfTotalWinningMoney += countsOf(rank) * winningMoneyOfRank.getAmount();
        }
        return amountOfTotalWinningMoney / (double) purchasedAmount.getAmount();
    }
}
