package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

public enum Rank {

    FIRST(2000000000, 6, (hitCounts, bonus) -> hitCounts == 6),
    SECOND(30000000, 5, (hitCounts, bonus) -> hitCounts == 5 && bonus),
    THIRD(1500000, 5, (hitCounts, bonus) -> hitCounts == 5 && !bonus),
    FOURTH(50000, 4, (hitCounts, bonus) -> hitCounts == 4),
    FIFTH(5000, 3, (hitCounts, bonus) -> hitCounts == 3),
    NOT_THING(0, 0, (hitCounts, bonus) -> hitCounts < 3 && hitCounts >= 0),
    ;

    private final long reward;
    private final int hitCounts;
    private final BiFunction<Integer, Boolean, Boolean> expression;

    Rank(final long reward, final int hitCounts, final BiFunction<Integer, Boolean, Boolean> expression) {
        this.reward = reward;
        this.hitCounts = hitCounts;
        this.expression = expression;
    }

    public static long calculateMoney(Rank currentRank, long count) {
        return currentRank.reward * count;
    }

    public static Rank calculateCurrentRank(int hitCounts, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.expression.apply(hitCounts, bonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 랭크가 없습니다."));
    }

    public static Map<Rank, Integer> initResultMap() {
        Map<Rank, Integer> rankMap = new TreeMap<>(rankRewardDescendingComparator());
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
