package lotto.domain;

enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    OTHER(0, 0);

    private final int matchCount;
    private final int money;

    Rank(final int matchCount, final int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    static Rank valueOf(final int matchCount, boolean existBonus) {
        if (existBonus && (matchCount == SECOND.matchCount)) {
            return SECOND;
        }

        for (Rank value : values()) {
            if (value != SECOND && (value.matchCount == matchCount)) {
                return value;
            }
        }
        return Rank.OTHER;
    }

    double prize(final double count) {
        return (money * count);
    }
}
