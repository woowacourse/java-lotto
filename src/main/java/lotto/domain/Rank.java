package lotto.domain;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final int RANK_MIN_COUNT = 3;

    private final int countOfMatch;
    private final int prize;

    private Rank(final int countOfMatch, final int prize) {
        this.countOfMatch = countOfMatch;
        this.prize = prize;
    }

    public static Rank valueOf(final int countOfMatch, final boolean bonusNo) {
        if (countOfMatch < RANK_MIN_COUNT) {
            return MISS;
        }

        if (bonusNo && SECOND.isMatchCount(countOfMatch)) {
            return SECOND;
        }

        for (final Rank rank : values()) {
            if (rank.isMatchCount(countOfMatch) && rank != SECOND) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + "는 유효하지 않은 값입니다.");
    }

    private boolean isMatchCount(final int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public int getPrize() {
        return prize;
    }
}
