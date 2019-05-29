package lotto.domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private final int rank;
    private final int money;

    Rank(int rank, int money) {
        this.rank = rank;
        this.money = money;
    }

    public static Rank valueOf(int count) {
        if (count < WINNING_MIN_COUNT) {
            return MISS;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(count)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(count + "는 유효하지 않은 값입니다.");
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
