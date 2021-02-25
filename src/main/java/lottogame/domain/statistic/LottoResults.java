package lottogame.domain.statistic;

import lottogame.domain.lotto.Money;
import lottogame.domain.dto.LottoResultsDto;

import java.util.*;

public class LottoResults {
    private final List<Rank> ranks;
    private int totalPrizeMoney = 0;

    public LottoResults(List<Rank> ranks) {
        this.ranks = new ArrayList<>(ranks);
    }

    public LottoResultsDto makeStatistics(Money money) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isNotFound())
                .forEach(rank -> calculatePrize(rank, result));
        return new LottoResultsDto(result, calculateProfit(money));
    }

    private void calculatePrize(Rank rank, Map<Rank, Integer> result) {
        int count = (int) ranks.stream()
                .filter(value -> rank.equals(value))
                .count();
        totalPrizeMoney += (count * rank.getMoney());
        result.put(rank, count);
    }

    private float calculateProfit(Money money) {
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
