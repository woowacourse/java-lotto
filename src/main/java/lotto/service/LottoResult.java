package lotto.service;

import lotto.domain.Rank;

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

    public Map<Rank, Integer> results() {
        return new TreeMap<>(results);
    }

    public AverageSummary calculate() {
        int sumOfRank = 0;
        int sumOfTickets = 0;
        for (Map.Entry<Rank, Integer> entry : results.entrySet()) {
            sumOfRank += entry.getKey().money() * entry.getValue();
            sumOfTickets += entry.getValue();
        }
        return new AverageSummary(sumOfRank, sumOfTickets);
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
