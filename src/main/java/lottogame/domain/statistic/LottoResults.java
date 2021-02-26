package lottogame.domain.statistic;

import lottogame.domain.lotto.Money;

import java.util.*;

public class LottoResults {
    private final List<Rank> ranks;
    private int totalPrizeMoney = 0;

    public LottoResults(List<Rank> ranks) {
        this.ranks = new ArrayList<>(ranks);
    }

    public Map<Rank, Integer> makeStatistics() {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isNotFound())
                .forEach(rank -> result.put(rank, countFrequency(rank)));
        return result;
    }

    private int countFrequency(Rank value) {
        return (int) ranks.stream()
                .filter(rank -> rank.equals(value))
                .count();
    }

    public float makeProfit(Map<Rank, Integer> statistics, Money money) {
        int totalPrizeMoney = 0;
        for (Rank rank : statistics.keySet()) {
            totalPrizeMoney += (statistics.get(rank) * rank.getMoney());
        }
        return money.divide(totalPrizeMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResults that = (LottoResults) o;
        return totalPrizeMoney == that.totalPrizeMoney && Objects.equals(ranks, that.ranks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranks, totalPrizeMoney);
    }
}
