package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NOT_THING(0, 0),
    ;

    private final long reward;
    private final int hitCounts;

    Rank(final long reward, final int hitCounts) {
        this.reward = reward;
        this.hitCounts = hitCounts;
    }

    public static long calculateMoney(final Rank currentRank, final long count) {
        return currentRank.reward * count;
    }

    public static Rank calculateCurrentRank(final int hitCounts, final boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameHitCount(hitCounts))
                .filter(rank -> rank.isFilterRankNotSecondOrHasBonusNumber(hasBonusNumber))
                .findFirst()
                .orElse(NOT_THING);
    }

    private boolean isFilterRankNotSecondOrHasBonusNumber(final boolean hasBonusNumber) {
        return !this.equals(SECOND) || hasBonusNumber;
    }

    private boolean isSameHitCount(final int hitCounts) {
        return this.hitCounts == hitCounts;
    }

    public boolean isNothing() {
        return reward == 0;
    }

    public long getReward() {
        return reward;
    }

    public int getHitCounts() {
        return hitCounts;
    }
}
