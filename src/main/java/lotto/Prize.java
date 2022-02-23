package lotto;

import java.util.Arrays;

public enum Prize {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private final int matchCount;
    private final int amount;

    Prize(int matchCount, int amount) {
        this.matchCount = matchCount;
        this.amount = amount;
    }

    public static Prize getPrize(int matchCount, boolean bonus) {
        if (matchCount != SECOND.matchCount) {
            return getPrize(matchCount);
        }
        if (bonus) {
            return SECOND;
        }
        return THIRD;
    }

    private static Prize getPrize(int matchCount) {
        return Arrays.stream(values())
            .filter(prize -> prize.matchCount == matchCount)
            .findFirst()
            .get();
    }
}
