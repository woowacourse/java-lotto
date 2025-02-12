package lotto;

public enum Rank {

    NO_PRIZE(0, false, 0) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount();
        }
    },
    FIFTH(3, false, 5_000) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount();
        }
    },
    FOURTH(4, false, 50_000) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount();
        }
    },
    THIRD(5, false, 1_500_000) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount()
                    && isBonusMatch != this.isBonusMatch();
        }
    },
    SECOND(5, true, 30_000_000) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount()
                    && isBonusMatch == this.isBonusMatch();
        }
    },
    FIRST(6, false, 2_000_000_000) {
        @Override
        boolean isMatch(int matchCount, boolean isBonusMatch) {
            return matchCount == this.getMatchCount();
        }
    },
    ;

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int winningAmount;

    Rank(int matchCount, boolean isBonusMatch, int winningAmount) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.winningAmount = winningAmount;
    }

    abstract boolean isMatch(int matchCount, boolean isBonusMatch);

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static Rank calculate(int matchCount, boolean isBonusMatch) {
        for (Rank rank : Rank.values()) {
            if (rank.isMatch(matchCount, isBonusMatch)) {
                return rank;
            }
        }
        return NO_PRIZE;
    }
}
