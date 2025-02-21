package model;

import java.util.Arrays;

public enum PrizeTier {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int matchedCount;
    private final int prize;

    PrizeTier(int matchedCount, int prize) {
        this.matchedCount = matchedCount;
        this.prize = prize;
    }

    public static PrizeTier getTier(int matchedCount, boolean bonusMatched) {
        if (matchedCount == 5 && bonusMatched) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(tier -> tier != SECOND)
                .filter(tier -> tier.matchedCount == matchedCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchedCount() {
        return matchedCount;
    }
}
