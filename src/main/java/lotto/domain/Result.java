package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Result {
    FIRST(6,2000000000),
    SECOND(5,30000000),
    THIRD(5,1500000),
    FOURTH(4,50000),
    FIFTH(3,5000),
    NONE(0,0);

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

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }
}
