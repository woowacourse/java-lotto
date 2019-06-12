package lotto;

import lotto.domain.Rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameResultDto {

    private final HashMap<Rank, Integer> results;
    private final double profit;

    private GameResultDto(final Map<Rank, Integer> results, Double profit) {
        this.results = new HashMap<>(results);
        this.profit = profit.doubleValue();
    }

    public static GameResultDto of(Map<Rank, Integer> counts, Double profit) {
        return new GameResultDto(counts, profit);
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
        if (!(o instanceof GameResultDto)) return false;
        GameResultDto that = (GameResultDto) o;
        return Double.compare(that.profit, profit) == 0 &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, profit);
    }
}
