package lotto.domain;

import java.util.*;

public enum Result {
    FIRST(6,2000000000),
    SECOND(5,30000000),
    THIRD(5,1500000),
    FOURTH(4,50000),
    FIFTH(3,5000),
    NONE(0,0);

    private final int count;
    private final int prize;

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

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
        Map<Result, Integer> statistics = new LinkedHashMap<>();
        for (Result result : values()) {
            statistics.put(result, 0);
        }

        for (Result result : results) {
            statistics.put(result, statistics.get(result) + 1);
        }
        List<Integer> firstToFifth = new ArrayList(statistics.values()).subList(0,5);
        Collections.reverse(firstToFifth);
        return firstToFifth;
    }
}
