package lotto.domain;

import java.util.*;

public enum Result {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    public static final int STATISTICS_INITIAL_VALUE = 0;
    public static final int RESULT_START_INDEX = 0;
    public static final int RESULT_END_INDEX = 5;

    private final int count;
    private final int prize;

    Result(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Result getResult(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(result -> result.count == matchCount)
                .filter(result -> !SECOND.equals(result) || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Integer> getStatistics(List<Result> results) {
        Map<Result, Integer> statistics = createStatistics(results);

        List<Integer> firstToFifth = new ArrayList(statistics.values()).subList(RESULT_START_INDEX, RESULT_END_INDEX);
        Collections.reverse(firstToFifth);
        return firstToFifth;
    }

    private static Map<Result, Integer> createStatistics(List<Result> results) {
        Map<Result, Integer> statistics = new LinkedHashMap<>();
        for (Result result : values()) {
            statistics.put(result, STATISTICS_INITIAL_VALUE);
        }
        for (Result result : results) {
            statistics.put(result, statistics.get(result) + 1);
        }
        return statistics;
    }

    public static int calculateProfit(List<Result> results) {
        return results.stream()
                .map(result -> result.prize)
                .reduce(0, Integer::sum);
    }

    public static List<Result> getResultsFromFifthToFirst() {
        List<Result> values = Arrays.asList(values()).subList(RESULT_START_INDEX, RESULT_END_INDEX);
        Collections.reverse(values);
        return values;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }
}
