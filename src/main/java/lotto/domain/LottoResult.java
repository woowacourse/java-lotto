package lotto.domain;

import lotto.dto.LottoResultDto;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class LottoResult {
    private final Map<Rank, Integer> results;

    public LottoResult() {
        this.results = new TreeMap<>();
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void plus(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public LottoResultDto results() {
        return new LottoResultDto(new TreeMap<>(results));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult result = (LottoResult) o;
        return results.equals(result.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}
