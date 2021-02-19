package lotto.domain;

import java.util.*;

public enum Result {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private static final int FIRST_INDEX = 0;
    private static final int FIFTH_INDEX = 5;

    private final int count;
    private final int prize;


    Result(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public static Result getResult(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(result -> result.count == matchCount)
                .filter(result -> !SECOND.equals(result) || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Integer> getStatistics(List<Result> results) {
        Map<Result, Integer> statistics = new LinkedHashMap<>();
        initStatistics(statistics);
        checkResult(results,statistics);

        List<Integer> firstToFifth = new ArrayList(statistics.values()).subList(FIRST_INDEX, FIFTH_INDEX);
        Collections.reverse(firstToFifth);
        return firstToFifth;
    }

    private static void initStatistics(Map<Result, Integer> statistics) {
        for (Result result : values()) {
            statistics.put(result, 0);
        }
    }

    private static void checkResult(List<Result> results, Map<Result, Integer> statistics) {
        for (Result result : results) {
            statistics.put(result, statistics.get(result) + 1);
        }
    }

    public static float calculateProfit(List<Result> results) {
        return (float) results.stream()
                .map(Result::getPrize)
                .reduce(0, (a, b) -> a + b);
    }
}
