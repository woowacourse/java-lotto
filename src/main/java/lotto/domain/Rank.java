package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiPredicate;

public enum Rank {

    FIRST(2_000_000_000, 6, (hitCounts, bonus) -> hitCounts == 6),
    SECOND(30_000_000, 5, (hitCounts, bonus) -> hitCounts == 5 && bonus),
    THIRD(1_500_000, 5, (hitCounts, bonus) -> hitCounts == 5 && !bonus),
    FOURTH(50_000, 4, (hitCounts, bonus) -> hitCounts == 4),
    FIFTH(5_000, 3, (hitCounts, bonus) -> hitCounts == 3),
    NOT_THING(0, 0, (hitCounts, bonus) -> hitCounts < 3 && hitCounts >= 0),
    ;

    private final long reward;
    private final int hitCounts;
    private final BiPredicate<Integer, Boolean> rankMatchPredicate;

    Rank(final long reward, final int hitCounts, final BiPredicate<Integer, Boolean> rankMatchPredicate) {
        this.reward = reward;
        this.hitCounts = hitCounts;
        this.rankMatchPredicate = rankMatchPredicate;
    }

    public static long calculateMoney(final Rank currentRank, final long count) {
        return currentRank.reward * count;
    }

    public static Rank calculateCurrentRank(final int hitCounts, final boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.rankMatchPredicate.test(hitCounts, bonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 랭크가 없습니다."));
    }

    public static Map<Rank, Integer> createInitResultMap() {
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
