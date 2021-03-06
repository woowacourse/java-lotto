package lottogame.domain.stats;

import java.util.*;

public class LottoResults {
    private final Map<Rank, Integer> results;
    private final Yield yield;

    public LottoResults(Map<Rank, Integer> results, Yield yield) {
        results.remove(Rank.NOT_FOUND);
        this.results = results;
        this.yield = yield;
    }

    public Map<Rank, Integer> values() {
        return new EnumMap<>(this.results);
    }

    public float yield() {
        return yield.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResults that = (LottoResults) o;
        return Objects.equals(results, that.results) && Objects.equals(yield, that.yield);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, yield);
    }
}
