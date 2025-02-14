package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0),

    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private int count;
    private int prize;

    Rank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Rank matchRank(int count, boolean isMatchBonus) {
        if (count == SECOND.count && isMatchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rating -> rating.count == count)
                .findFirst()
                .orElse(NONE);
    }

    public static long calculateTotalPrize(Rank rank, Integer count) {
        return (long) rank.prize * count;
    }
}
