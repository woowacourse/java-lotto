package lotto;

public enum Rank {
    NONE(0, 0),
    FIFTH(5000, 3),
    FOURTH(50000, 4),
    THIRD(1500000, 5),
    SECOND(30000000, 5),
    FIRST(2000000000, 6);

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
        for (Rank rank : Rank.values()) {
            if (matchCount == SECOND.matchCount) {
                return checkSecondOrThird(hasBonusNumber);
            }
            if (rank.matchCount == matchCount) {
                return rank;
            }
        }
        return NONE;
    }

    private static Rank checkSecondOrThird(final boolean hasBonusNumber) {
        if (hasBonusNumber) {
            return SECOND;
        }
        return THIRD;
    }
}