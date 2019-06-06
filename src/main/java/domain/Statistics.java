package domain;

import java.util.EnumMap;

class Statistics {
    private EnumMap<Rank, CountOfRank> countsOfRanks;

    private Statistics(EnumMap<Rank, CountOfRank> countsOfRanks) {
        this.countsOfRanks = countsOfRanks;
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

    void add(Rank matchingResult) {
        countsOfRanks.get(matchingResult).countUp();
    }

    int countsOf(Rank matchingResult) {
        CountOfRank countOfRank = countsOfRanks.get(matchingResult);

        return countOfRank.getCount();
    }

    double calculateEarningRatesOf(Purchasement purchasement) {
        int winningMoneyOfRank;
        int totalWinningMoney = 0;

        for (Rank rank : countsOfRanks.keySet()) {
            winningMoneyOfRank = rank.getWinningMoney();
            totalWinningMoney += countsOf(rank) * winningMoneyOfRank;
        }
        return totalWinningMoney / (double) purchasement.getPurchaseAmount();
    }
}
