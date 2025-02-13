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
        if (matchCount == SECOND.matchCount && hasBonusNumber) {
            return SECOND;
        }
        if (matchCount == THIRD.matchCount) {
            return THIRD;
        }
        for (final Prize prize : Prize.values()) {
            if (prize.getMatchCount() == matchCount) {
                return prize;
            }
        }
        return NONE;
    }
}