package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L, false),
    SECOND(5, 30_000_000L, true),
    THIRD(5, 1_500_000L, false),
    FOURTH(4, 50_000L, false),
    FIFTH(3, 5_000L, false),
    NO_MATCH(0, 0L, false);

    private final int sameNumberCount;
    private final long prize;
    private final boolean containsBonusNumber;

    Rank(int rank, long prize, boolean containsBonusNumber) {
        this.sameNumberCount = rank;
        this.prize = prize;
        this.containsBonusNumber = containsBonusNumber;
    }

    public static Rank of(long sameNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.sameNumberCount == sameNumberCount && rank.containsBonusNumber == hasBonusNumber)
                .findAny()
                .orElse(NO_MATCH);
    }

    public long getPrize() {
        return prize;
    }

    public Rank getThis() {
        return this;
    }
}
