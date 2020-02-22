package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private int hitCount;
    private int reward;

    Rank(int hitCount, int reward) {
        this.hitCount = hitCount;
        this.reward = reward;
    }

    public static Rank getRank(int hitCount, boolean hasBonusNumber) {
        Rank rank = Stream.of(Rank.values())
                .filter(x -> isSameHitCount(hitCount, x))
                .findFirst()
                .orElse(null);

        if (rank == SECOND && !hasBonusNumber) {
            return THIRD;
        }

        return rank;
    }

    private static boolean isSameHitCount(int hitCount, Rank rank) {
        return rank.getHitCount() == hitCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getReward() {
        return reward;
    }
}
