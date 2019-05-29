package lotto.domain;

import lotto.domain.game.ResultCounter;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
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

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(matchCount) && bonusMatch) {
            return SECOND;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(matchCount)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(matchCount + INVALID_RANGE);
    }

    public int totalAmount(ResultCounter resultCounter) {
        return resultCounter.multiply(this.getPrize());
    }

    private boolean matchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}
