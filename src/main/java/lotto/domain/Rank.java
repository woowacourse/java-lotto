package lotto.domain;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    FIRST(6, 2_000_000_000);

    private static final String INVALID_RANGE = "는 유효하지 않은 값입니다.";
    private static final int WINNING_MIN_COUNT = 3;

    private int matchCount;
    private int prize;

    private Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank valueOf(int matchCount) {
        if (matchCount < WINNING_MIN_COUNT) {
            return MISS;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(matchCount)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(matchCount + INVALID_RANGE);
    }

    private boolean matchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}
