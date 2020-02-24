package lotto.domain.result;

import lotto.domain.PurchaseMoney;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameResult {
    public static final int PERCENTAGE = 100;
    private final Map<Rank, Count> statistic;

    public GameResult() {
        statistic = new ConcurrentHashMap<>();
        for (Rank rank : Rank.values()) {
            statistic.put(rank, new Count());
        }
    }

    public double calculateProfit(PurchaseMoney money) {
        double profit = statistic.keySet().stream()
                .mapToDouble(rank -> rank.getPrize() * getCountByRank(rank).getCount())
                .sum();
        return profit / money.getPurchaseMoney() * PERCENTAGE;
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
}
