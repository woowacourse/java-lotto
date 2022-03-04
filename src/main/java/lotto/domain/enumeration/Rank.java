package lotto.domain.enumeration;

import java.util.Arrays;

public enum Rank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FORTH(50_000, 4),
    FIFTH(5_000, 3),
    NOTHING(0, 0);

    private static final int SECOND_PRICE_COUNT = 5;

    private final int prizeMoney;
    private final int count;

    Rank(int prizeMoney, int count) {
        this.prizeMoney = prizeMoney;
        this.count = count;
    }

    public static Rank of(int count, boolean isBonus) {
        if (isBonus && count == SECOND_PRICE_COUNT) {
            return Rank.SECOND;
        }

        return Arrays.stream(values())
                .filter(value -> value != Rank.SECOND)
                .filter(value -> value.count == count)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
