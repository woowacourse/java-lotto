package lotto.domain;

import java.util.Map;
import java.util.TreeMap;

public class LottoResult {
    private Map<Rank, Integer> results;

    public LottoResult(Map<Rank, Integer> results) {
        this.results = results;
    }

    public LottoResult() {
        this.results = new TreeMap<>();
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void plus(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    @Override
    public String toString() {
        return "LottoResult{" +
                "results=" + results +
                '}';
    }
}
