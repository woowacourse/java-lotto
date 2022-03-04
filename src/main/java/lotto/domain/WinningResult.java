package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WinningResult {
    private static final int INITIAL_COUNT = 0;

    private final Map<Ranking, Integer> value;

    public WinningResult(List<Ranking> rankings) {
        value = initialMap(rankings);
    }

    private Map<Ranking, Integer> initialMap(List<Ranking> rankings) {
        final Map<Ranking, Integer> result = new HashMap<>();
        for (Ranking ranking : Ranking.values()) {
            result.put(ranking, INITIAL_COUNT);
        }
        putValues(result, rankings);
        return Collections.unmodifiableMap(result);
    }

    private void putValues(Map<Ranking, Integer> result, List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            int count = result.get(ranking);
            result.put(ranking, ++count);
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
    public String toString() {
        return "WinningResult{" +
                "winningResult=" + value +
                '}';
    }
}
