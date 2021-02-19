package lotto.domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Result {

    FIRST(6, 2_000_000_000, " "),
    SECOND(5, 30_000_000, ", 보너스 볼 일치"),
    THIRD(5, 1_500_000, " "),
    FOURTH(4, 50_000," "),
    FIFTH(3, 5_000, " "),
    NONE(0, 0, " ");

    private static final int FIRST_INDEX = 0;
    private static final int FIFTH_INDEX = 5;

    private final int count;
    private final int prize;
    private final String bonus;


    Result(int count, int prize, String bonus) {
        this.count = count;
        this.prize = prize;
        this.bonus = bonus;
    }

    public static Result decisionLottoRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(result -> result.count == matchCount)
                .filter(result -> !SECOND.equals(result) || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Integer> getStatistics(List<Result> results) {
        Map<Result,Integer> statistics = Arrays.stream(Result.values())
                .collect(Collectors.toMap(Function.identity(), value -> 0,(key1,key2) -> key1,LinkedHashMap::new));
        checkResult(results, statistics);

        List<Integer> firstToFifth = new ArrayList(statistics.values()).subList(FIRST_INDEX, FIFTH_INDEX);
        Collections.reverse(firstToFifth);
        return firstToFifth;
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

    public static List<Result> getResultValues() {
        List<Result> values = Arrays.asList(Result.values());
        Collections.reverse(values);
        return values;
    }


    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public String getBonus() {
        return bonus;
    }
}
