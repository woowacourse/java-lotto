package domain;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),

    NONE(0, 0, false);

    private final int count;
    private final int prize;
    private final boolean isBonusMatched;

    Rank(int count, int prize, boolean isBonusMatched) {
        this.count = count;
        this.prize = prize;
        this.isBonusMatched = isBonusMatched;
    }

    public static Rank matchRank(int matchCount, boolean isBonusMatched) {
        for (Rank rank : Rank.values()) {
            if (rank.count == matchCount && rank.isBonusMatched == isBonusMatched) {
                return rank;
            }
        }
        return NONE;
    }

    public static long calculateTotalPrize(Rank rank, Integer count) {
        return (long) rank.prize * count;
    }

    public int getPrize() {
        return prize;
    }
}
