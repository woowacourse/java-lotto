package model.winning;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int matchCount;
    private final int prizeMoney;

    Rank(final int matchCount, final int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Rank valueOf(final int matchCount, final boolean matchBonus) {
        if (matchCount == 5) {
            return checkSecondOrThird(matchBonus);
        }
        return Arrays.stream(values())
                .filter(value -> value.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    private static Rank checkSecondOrThird(final boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
