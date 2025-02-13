package model;

import java.util.Arrays;

public enum PrizeTier {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private int matchedCount;
    private boolean isBonusMatched;
    private int prize;

    PrizeTier(int matchedCount, boolean isBonusMatched, int prize) {
        this.matchedCount = matchedCount;
        this.isBonusMatched = isBonusMatched;
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

    public boolean isBonusMatched() {
        return isBonusMatched;
    }
}
