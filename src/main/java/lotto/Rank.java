package lotto;

import java.util.Arrays;

public enum Rank {

    NO_PRIZE(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    ;

    private final int matchCount;
    private final boolean requiresBonusMatch;
    private final int winningAmount;

    Rank(int matchCount, boolean requiresBonusMatch, int winningAmount) {
        this.matchCount = matchCount;
        this.requiresBonusMatch = requiresBonusMatch;
        this.winningAmount = winningAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean requiresBonusMatch() {
        return requiresBonusMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static Rank classifyRank(int matchCount, boolean isBonusMatch) {
        if (matchCount == THIRD.matchCount) {
            return getRankWithBonus(isBonusMatch);
        }
        return getRankWithoutBonus(matchCount);
    }

    private static Rank getRankWithBonus(boolean isBonusMatch) {
        if (isBonusMatch) {
            return SECOND;
        }
        return THIRD;
    }

    private static Rank getRankWithoutBonus(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && !rank.requiresBonusMatch)
                .findFirst()
                .orElse(NO_PRIZE);
    }
}
