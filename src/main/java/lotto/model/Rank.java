package lotto.model;

import java.util.Arrays;

public enum Rank {

    FIRST(6, 2000000000L),
    SECOND(5, 30000000L),
    THIRD(5, 1500000L),
    FOURTH(4, 50000L),
    FIFTH(3, 5000L),
    NONE(0, 0L);

    private final int matchingCount;
    private final long winningAmount;

    Rank(final int matchingCount, final long winningAmount) {
        this.matchingCount = matchingCount;
        this.winningAmount = winningAmount;
    }

    public static Rank findBy(final int matchingCount, final boolean hasBonusNumber) {
        Rank findRank = Arrays.stream(values())
                .filter(rank -> rank.matchingCount == matchingCount)
                .findFirst()
                .orElse(NONE);
        if (findRank.equals(SECOND) && !hasBonusNumber) {
            return THIRD;
        }
        return findRank;
    }

}
