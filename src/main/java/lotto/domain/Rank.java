package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NONE(0, 0);

    private final int matchCount;
    private final long reward;

    Rank(int matchCount, long reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        if (isSecond(matchCount, matchBonus)) {
            return SECOND;
        }
        return findOtherRanks(matchCount);
    }

    public static long calculateReward(List<Rank> ranks) {
        return ranks.stream()
            .mapToLong(rank -> rank.reward)
            .sum();
    }

    public int findRewardCount(List<Rank> ranks) {
        return (int)ranks.stream()
            .filter(rank -> rank == this)
            .count();
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public long getReward() {
        return this.reward;
    }

    private static boolean isSecond(int matchCount, boolean matchBonus) {
        return SECOND.matchCount == matchCount && matchBonus;
    }

    private static Rank findOtherRanks(int matchCount) {
        return Arrays.stream(Rank.values())
            .filter(rank -> matchCount == rank.matchCount)
            .filter(rank -> rank != SECOND)
            .findAny()
            .orElse(NONE);
    }
}
