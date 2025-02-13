package lotto;

public enum Prize {
    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3),
    NONE(0, 0);

    private final int winningAmount;
    private final int matchCount;

    Prize(final int winningAmount, final int matchCount) {
        this.winningAmount = winningAmount;
        this.matchCount = matchCount;
    }

    private int getMatchCount() {
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
        for (Prize prize : Prize.values()) {
            if (prize.getMatchCount() == matchCount) {
                return prize;
            }
        }
        return NONE;
    }
}