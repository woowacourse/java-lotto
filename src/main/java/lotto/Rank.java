package lotto;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0),
    FIFTH(5_000, 3),
    FOURTH(50_000, 4),
    THIRD(1_500_000, 5),
    SECOND(30_000_000, 5),
    FIRST(2_000_000_000, 6);

    private final int winningAmount;
    private final int matchCount;

    Rank(final int winningAmount, final int matchCount) {
        this.winningAmount = winningAmount;
        this.matchCount = matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static Rank checkRank(final int matchCount, final boolean hasBonusNumber) {
        if (matchCount == SECOND.matchCount) {
            return checkSecondOrThird(hasBonusNumber);
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findAny()
                .orElse(NONE);
    }

    public int calculateAllWinningAmount(final int winningCount) {
        return winningCount * winningAmount;
    }

    private static Rank checkSecondOrThird(final boolean hasBonusNumber) {
        if (hasBonusNumber) {
            return SECOND;
        }
        return THIRD;
    }
}
