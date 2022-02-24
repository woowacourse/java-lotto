package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000L),
    SECOND(5, 30000000L),
    THIRD(5, 1500000L),
    FOURTH(4, 50000L),
    FIFTH(3, 5000L),
    NO_MATCH(0, 0L);

    private final int sameNumberCount;
    private final long prize;

    Rank(int sameNumberCount, long prize) {
        this.sameNumberCount = sameNumberCount;
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

    public int getSameNumberCount() {
        return sameNumberCount;
    }
}
