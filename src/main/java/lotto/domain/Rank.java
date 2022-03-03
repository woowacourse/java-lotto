package lotto.domain;

import java.util.Arrays;

public enum Rank {

    NOTHING(0, 0),
    FIFTH(5_000, 3),
    FORTH(50_000, 4),
    THIRD(1_500_000, 5),
    SECOND(30_000_000, 5),
    FIRST(2_000_000_000, 6);

    private final int prizeMoney;
    private final int count;

    Rank(int prizeMoney, int count) {
        this.prizeMoney = prizeMoney;
        this.count = count;
    }

    public static Rank of(int count, boolean isBonus) {
        if (Rank.SECOND.count == count && isBonus) {
            return Rank.SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != Rank.SECOND && rank.count == count)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getCount() {
        return count;
    }
}
