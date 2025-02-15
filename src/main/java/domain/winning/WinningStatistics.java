package domain.winning;

public enum WinningStatistics {

    NONE(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    FIVE_WITH_BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    public static final int SIZE_WITH_BONUS = 2;

    private final int matchCount;
    private final int prizeMoney;
    private final boolean isBonusMatched;

    WinningStatistics(int matchCount, int prizeMoney, boolean isBonusMatched) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonusMatched = isBonusMatched;
    }

    public boolean isMatchCount(int value) {
        return matchCount == value;
    }

    public boolean isBonusMatched(boolean value) {
        return isBonusMatched == value;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
