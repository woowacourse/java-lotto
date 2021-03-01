package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Statistics {
    private final Map<Result, Integer> statistic;

    public Statistics(List<Result> results) {
        this.statistic = Arrays.stream(Result.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        value -> 0, (key1, key2) -> key1,
                        LinkedHashMap::new)
                );
        putResult(results);
    }

    private void putResult(List<Result> results) {
        for (Result result : results) {
            statistic.put(result, statistic.get(result) + 1);
        }
    }

    public int getRankCount(Result result) {
        return statistic.get(result);
    }
}
