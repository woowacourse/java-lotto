package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Result {
    private static final String FORMAT = "%.2f";
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();

    public Result(final List<Rank> wins) {
        for (final Rank rank : Rank.values()) {
            final int rankCount = (int) wins.stream()
                    .filter(win -> win != Rank.LOSE && win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }

    private static int findEarning() {
        int sumOfPrize = 0;
        for (final Map.Entry<Rank, Integer> singleCount : countByRank.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        return sumOfPrize;
    }

    public String findEarningRate(final Money money) {
        final int earning = findEarning();
        final double earningRate = (double) earning / (double) money.getMoney();
        return String.format(FORMAT, earningRate);
    }

    public Map<Rank, Integer> getCountByRank() {
        return countByRank;
    }
}
