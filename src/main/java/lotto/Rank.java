package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6),
    SECOND(5),
    THIRD(5),
    FOURTH(4),
    FIFTH(3),
    NONE(0);

    private final int matchCount;

    Rank(int matchCount) {
        this.matchCount = matchCount;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        if (isSecond(matchCount, matchBonus)) {
            return Rank.SECOND;
        }

        return getRank(countMatchBonus(matchCount, matchBonus));
    }

    private static boolean isSecond(int matchCount, boolean matchBonus) {
        return equalMatchCount(Rank.SECOND.matchCount, matchCount) && matchBonus;
    }

    private static int countMatchBonus(int matchCount, boolean matchBonus) {
        if (matchBonus) {
            matchCount++;
        }

        return matchCount;
    }

    private static Rank getRank(int matchCount) {
        return Arrays.stream(Rank.values())
            .filter(rank -> equalMatchCount(matchCount, rank.matchCount))
            .filter(Rank::isNotSecond)
            .findAny()
            .orElse(Rank.NONE);
    }

    private static boolean equalMatchCount(int matchCount, int otherMatchCount) {
        return matchCount == otherMatchCount;
    }

    private static boolean isNotSecond(Rank rank) {
        return rank != Rank.SECOND;
    }
}
