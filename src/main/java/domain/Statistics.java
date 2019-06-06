package domain;

import java.util.EnumMap;

public class Statistics {
    private static final int INITIAL_PURCHASED_AMOUNT = 0;

    private EnumMap<Rank, CountOfRank> countsOfRanks;
    private int purchasedAmount;

    private Statistics(EnumMap<Rank, CountOfRank> countsOfRanks) {
        this.countsOfRanks = countsOfRanks;
        purchasedAmount = INITIAL_PURCHASED_AMOUNT;
    }

    static Statistics of(Rank[] values) {
        EnumMap<Rank, CountOfRank> countsOfRanks = new EnumMap<>(Rank.class);

        initialize(countsOfRanks, values);
        return new Statistics(countsOfRanks);
    }

    private static void initialize(EnumMap<Rank, CountOfRank> countsOfRanks, Rank[] values) {
        for(Rank rank : values) {
            countsOfRanks.put(rank, new CountOfRank());
        }
    }

    void add(Rank rank) {
        countsOfRanks.get(rank).countUp();
        purchasedAmount += Lotto.PRICE;
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
        return totalWinningMoney / (double) purchasedAmount;
    }
}
