package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Rank {
    FIRST(6, 2_000_000_000),
    BONUS(-1, 30_000_000),
    SECOND(5, 150_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    private int matchCount;
    private int prizeAmount;

    public static Map<Rank, Integer> result = new HashMap<>();

    Rank(int matchCount, int prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public static Rank find(int matchCount) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.matchCount == matchCount)
            .findFirst()
            .orElse(NONE);
    }

    public int getPrize() {
        return prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static void reset() {
        result.clear();
    }
}
