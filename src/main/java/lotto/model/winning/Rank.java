package lotto.model.winning;

import java.util.Arrays;

public enum Rank {

    NONE(0, false, 0L),
    FIFTH(3, false, 5_000L),
    FOURTH(4, false, 50_000L),
    THIRD(5, false, 1_500_000L),
    SECOND(5, true, 30_000_000L),
    FIRST(6, false, 2_000_000_000L);

    private final int matchingCount;
    private final boolean hasBonus;
    private final long winningAmount;

    Rank(final int matchingCount, final boolean hasBonus, final long winningAmount) {
        this.matchingCount = matchingCount;
        this.hasBonus = hasBonus;
        this.winningAmount = winningAmount;
    }

    public static Rank findBy(final int matchingCount, final boolean hasBonusNumber) {
        Rank findRank = Arrays.stream(values())
                .filter(rank -> rank.matchingCount == matchingCount)
                .findFirst()
                .orElse(NONE);
        if (findRank.equals(THIRD) && SECOND.hasBonus == hasBonusNumber) {
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

    public boolean isNone() {
        return this.equals(NONE);
    }

    public boolean hasBonus() {
        return this.hasBonus;
    }
}
