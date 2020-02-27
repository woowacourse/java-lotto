package Lotto.domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6, new Reward(2_000_000_000)),
    SECOND(5, new Reward(30_000_000)),
    THIRD(5, new Reward(1_500_000)),
    FOURTH(4, new Reward(50_000)),
    FIFTH(3, new Reward(5_000));

    private int hitCount;
    private Reward reward;

    Rank(int hitCount, Reward reward) {
        this.hitCount = hitCount;
        this.reward = reward;
    }

    public static Rank getRank(int hitCount, boolean hasBonusNumber) {
        Rank rank = Stream.of(Rank.values())
                .filter(t -> isSameHitCount(hitCount, t))
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
        return this.hitCount;
    }

    public int getRankReward() {
        return reward.getReward();
    }
}
