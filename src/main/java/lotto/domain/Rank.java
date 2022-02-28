package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
                .filter(rank -> !rank.equals(SECOND) || hasBonusNumber)
                .findFirst()
                .orElse(NOT_THING);
    }

    public static Map<Rank, Integer> initResultMap() {
        final Map<Rank, Integer> rankMap = new TreeMap<>(rankRewardDescendingComparator());
        Arrays.stream(values())
                .forEach(rank -> rankMap.put(rank, defaultCount()));
        return rankMap;
    }

    private static Comparator<Rank> rankRewardDescendingComparator() {
        return (o1, o2) -> Long.compare(o1.reward, o2.reward);
    }

    private static int defaultCount() {
        return 0;
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
