package lotto.domain.result;

import lotto.domain.PurchaseMoney;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameResult {
    private final Map<Rank, Count> statistic;
    private double profit;

    public GameResult(List<Rank> ranks) {
        statistic = new ConcurrentHashMap<>();
        for (Rank rank : ranks) {
            statistic.put(rank, new Count());
        }
        this.profit = 0;
    }

    public void calculateProfit(PurchaseMoney money) {
        profit = statistic.keySet().stream()
                .mapToDouble(rank -> rank.getPrize() * getCountByRank(rank).getCount())
                .sum();
        profit = profit / money.getPurchaseMoney() * 100;
    }

    public void count(Rank rank) {
        if (rank.isNotDefault()) {
            statistic.get(rank).addCount();
        }
    }

    public Map<Rank, Count> getStatistic() {
        return statistic;
    }

    public Count getCountByRank(Rank rank) {
        return statistic.get(rank);
    }

    public double getProfit() {
        return profit;
    }
}
