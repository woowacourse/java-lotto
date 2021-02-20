package lotto.domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Statistics{
    private Map<Result, Integer> statistic;

    public Statistics(List<Result> results){
        this.statistic = statistics();
        checkResult(results);
    }

    public Map<Result, Integer> statistics() {
        Map<Result,Integer> statistics = Arrays.stream(Result.values())
                .collect(Collectors.toMap(Function.identity(), value -> 0,(key1, key2) -> key1, LinkedHashMap::new));

        return statistics;
    }

    private void checkResult(List<Result> results) {
        for (Result result : results) {
            statistic.put(result, statistic.get(result) + 1);
        }
    }

    public int getStatic(Result result){
        return statistic.get(result);
    }
}
