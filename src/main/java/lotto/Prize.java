package lotto;

public enum Prize {
    NONE(0, 0),
    FIFTH(5000, 3),
    FOURTH(50000, 4),
    THIRD(1500000, 5),
    SECOND(30000000, 5),
    FIRST(2000000000, 6);

    private final int winningAmount;
    private final int matchCount;

    Prize(final int winningAmount, final int matchCount) {
        this.winningAmount = winningAmount;
        this.matchCount = matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static Prize getPrize(final int matchCount, final boolean hasBonusNumber) {
        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }
        if (matchCount == SECOND.matchCount) {
            return getSecondOrThird(hasBonusNumber);
        }
        if (matchCount == FOURTH.matchCount) {
            return FOURTH;
        }
        if (matchCount == FIFTH.matchCount) {
            return FIFTH;
        }
        return NONE;
    }

    private static Prize getSecondOrThird(final boolean hasNumber) {
        if (hasNumber) {
            return SECOND;
        }
        return THIRD;
    }
}