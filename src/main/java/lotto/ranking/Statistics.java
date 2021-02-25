package lotto.ranking;

import lotto.money.Money;

import java.util.*;

import static lotto.game.LottoCount.ZERO;

public class Statistics {
    private final Map<Ranking, Integer> statistics;

    public Statistics(List<Ranking> rankings) {
        statistics = new EnumMap<>(Ranking.class);
        for (Ranking ranking : Ranking.values()) {
            statistics.put(ranking, ZERO);
        }
        calculateStatistics(rankings);
    }

    private void calculateStatistics(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            statistics.computeIfPresent(ranking, (Ranking key, Integer value) -> ++value);
        }
    }

    public double calculateProfit(Money purchaseMoney) {
        return purchaseMoney.calculateProfit(getTotalPrize());
    }

    public int findRankingCount(Ranking ranking) {
        return statistics.get(ranking);
    }

    public int getTotalPrize() {
        int prize = 0;
        for (Ranking ranking : Ranking.values()) {
            prize += ranking.calculatePrize(findRankingCount(ranking));
        }
        return prize;
    }

    public Map<Ranking, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
