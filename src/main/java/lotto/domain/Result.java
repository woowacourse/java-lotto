package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Result {
    private static final String FORMAT = "%.2f";
    private static final Map<Rank, Integer> countByRank = new TreeMap<>();

    public Result(List<Rank> wins) {
        for (Rank rank : Rank.values()) {
            int rankCount = (int) wins.stream()
                    .filter(win -> win != Rank.LOSE && win == rank).count();
            countByRank.put(rank, rankCount);
        }
    }

    public String findEarningRate(Money money) {
        int earning = findEarning();
        double earningRate = (double) earning / (double) money.getMoney();
        return String.format(FORMAT, earningRate);
    }

    private static int findEarning() {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : countByRank.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        return sumOfPrize;
    }

    public Map<Rank, Integer> getCountByRank() {
        return countByRank;
    }
}
