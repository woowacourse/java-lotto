package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Result {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int count;
    private final int prize;

    Result(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Result decisionLottoRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(result -> result.count == matchCount)
                .filter(result -> !SECOND.equals(result) || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public static float calculateProfit(List<Result> results) {
        return (float) results.stream()
                .map(Result::getPrize)
                .reduce(0, (a, b) -> a + b);
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }
}
