package lotto.domain;

import java.util.Arrays;

public enum Rank {

    NOTHING(0, 0, false),
    FIFTH(5_000, 3, false),
    FORTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final int prizeMoney;
    private final int count;
    private final boolean isBonus;

    Rank(int prizeMoney, int count, boolean isBonus) {
        this.prizeMoney = prizeMoney;
        this.count = count;
        this.isBonus = isBonus;
    }

    public static Rank of(int count, boolean isBonus) {
        if (isBonus) {
            return Rank.SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> !rank.isBonus)
                .filter(rank -> rank.count == count)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonus() {
        return isBonus;
    }
}
