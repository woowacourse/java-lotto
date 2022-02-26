package lotto.domain.enumeration;

import java.util.Arrays;

public enum Rank {

    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FORTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NOTHING(0, 0, false);

    public static final int SECOND_PRICE_COUNT = 5;
    private final int prizeMoney;
    private final int count;
    private final boolean isBonus;

    Rank(int prizeMoney, int count, boolean isBonus) {
        this.prizeMoney = prizeMoney;
        this.count = count;
        this.isBonus = isBonus;
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
