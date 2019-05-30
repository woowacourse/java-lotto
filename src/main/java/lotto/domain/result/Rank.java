package lotto.domain.result;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;
    private static final String INVALID_COUNT_ERROR = "는 유효하지 않은 값입니다.";

    private final int rank;
    private final int money;

    Rank(int rank, int money) {
        this.rank = rank;
        this.money = money;
    }

    public static Rank valueOf(int count, boolean isBonusNum) {
        if (count < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(count) && isBonusNum) {
            return SECOND;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(count) && !rank.equals(SECOND)) {
                return rank;
            }
        }

        throw new InvalidRankCount(count + INVALID_COUNT_ERROR);
    }

    private boolean matchCount(int count) {
        return this.rank == count;
    }

    public int getRank() {
        return rank;
    }

    public int getMoney() {
        return money;
    }
}
