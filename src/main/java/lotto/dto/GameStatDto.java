package lotto.dto;

import lotto.domain.GameResultMatcher;
import lotto.domain.Rank;
import lotto.util.GameResultDtoConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameStatDto {

    private final HashMap<Rank, Integer> results;
    private final double profit;

    private GameStatDto(final Map<Rank, Integer> results, Double profit) {
        this.results = new HashMap<>(results);
        this.profit = profit.doubleValue();
    }

    public static GameStatDto of(Map<Rank, Integer> counts, Double profit) {
        return new GameStatDto(counts, profit);
    }

    public static GameStatDto of(final GameResultMatcher result) {
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
        if (!(o instanceof GameStatDto)) return false;
        GameStatDto that = (GameStatDto) o;
        return Double.compare(that.profit, profit) == 0 &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, profit);
    }
}
