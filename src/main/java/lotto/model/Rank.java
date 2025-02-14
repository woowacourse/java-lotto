package lotto.model;

import java.util.Arrays;

public enum Rank {

    NONE(0, 0L),
    FIFTH(3, 5_000L),
    FOURTH(4, 50_000L),
    THIRD(5, 1_500_000L),
    SECOND(5, 30_000_000L),
    FIRST(6, 2_000_000_000L);

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
        if (findRank.equals(THIRD) && hasBonusNumber) {
            return SECOND;
        }
        return findRank;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public long getWinningAmount() {
        return winningAmount;
    }

    public boolean isSecond() {
        return this.equals(SECOND);
    }

    public boolean isNone() {
        return this.equals(NONE);
    }

}
