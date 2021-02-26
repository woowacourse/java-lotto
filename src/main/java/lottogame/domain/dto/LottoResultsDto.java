package lottogame.domain.dto;

import lottogame.domain.statistic.Rank;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class LottoResultsDto {
    private final Map<Rank, Integer> results;
    private final float profit;

    public LottoResultsDto(Map<Rank, Integer> results, float profit) {
        this.results = results;
        this.profit = profit;
    }

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }

    public float getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResultsDto that = (LottoResultsDto) o;
        return Float.compare(that.profit, profit) == 0 && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, profit);
    }
}
