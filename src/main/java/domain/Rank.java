package domain;

import java.util.Arrays;

public enum Rank {
    SIXTH(0, 0, false),
    FIFTH(3, 5000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false);

    private static final int SECOND_THIRD_RANK_COUNT = 5;
    private final int count;
    private final int winningPrice;
    private final boolean hasBonusBall;

    Rank(int count, int winningPrice, boolean hasBonusBall) {
        this.count = count;
        this.winningPrice = winningPrice;
        this.hasBonusBall = hasBonusBall;
    }

    public static Rank valueOf(int count, boolean hasBonusBall) {
        if (count == SECOND_THIRD_RANK_COUNT && hasBonusBall) {
            return Rank.SECOND;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.count == count)
                .filter(rank -> !rank.hasBonusBall)
                .findFirst()
                .orElse(SIXTH);
    }

    public int getWinningPrice() {
        return winningPrice;
    }

    public int getCount() {
        return count;
    }

    public boolean hasBonusBall() {
        return hasBonusBall;
    }
}