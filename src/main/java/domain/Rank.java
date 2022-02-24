package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NO_MATCH(0, 0L);

    private final int sameNumberCount;
    private final long prize;

    Rank(int rank, long prize) {
        this.sameNumberCount = rank;
        this.prize = prize;
    }

    public static Rank of(int sameNumberCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameNumberCount(sameNumberCount))
                .findAny()
                .orElse(NO_MATCH);
    }

    public boolean isSameNumberCount(int count) {
        return sameNumberCount == count;
    }

    public long getPrize() {
        return prize;
    }
}
