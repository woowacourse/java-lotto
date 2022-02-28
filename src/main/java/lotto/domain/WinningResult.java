package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

final public class WinningResult {
    private static final int INITIAL_COUNT = 0;

    private final Map<Ranking, Integer> value = new HashMap<>();

    public WinningResult(List<Ranking> rankings) {
        initialMap();
        putValues(rankings);
    }

    private void initialMap() {
        for (Ranking ranking : Ranking.values()) {
            value.put(ranking, INITIAL_COUNT);
        }
    }

    private void putValues(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            int count = value.get(ranking);
            value.put(ranking, ++count);
        }
    }

    public long calculatePrizeSum() {
        return value.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().multiplyPrizeWithCount(entry.getValue()))
                .sum();
    }

    public Map<Ranking, Integer> getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningResult that = (WinningResult) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WinningResult{" +
                "winningResult=" + value +
                '}';
    }
}
