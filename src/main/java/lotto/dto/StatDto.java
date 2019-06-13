package lotto.dto;

import lotto.domain.GameResultMatcher;
import lotto.domain.Rank;
import lotto.util.GameResultDtoConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatDto {

    private final HashMap<Rank, Integer> results;
    private final double profit;

    private StatDto(final Map<Rank, Integer> results, Double profit) {
        this.results = new HashMap<>(results);
        this.profit = profit.doubleValue();
    }

    public static StatDto of(Map<Rank, Integer> counts, Double profit) {
        return new StatDto(counts, profit);
    }

    public static StatDto of(final GameResultMatcher result) {
        return new GameResultDtoConverter().convertResultToDto(result);
    }

    public Integer getCount(final Rank rank) {
        return results.get(rank);
    }

    public Double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatDto)) return false;
        StatDto that = (StatDto) o;
        return Double.compare(that.profit, profit) == 0 &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, profit);
    }
}
