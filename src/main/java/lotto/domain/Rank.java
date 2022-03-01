package lotto.domain;

import java.util.*;

public enum Rank {

    FIRST(6, new Reward(2_000_000_000L)),
    SECOND(5, new Reward(30_000_000L)),
    THIRD(5, new Reward(1_500_000L)),
    FOURTH(4, new Reward(50_000L)),
    FIFTH(3, new Reward(5_000L)),
    NONE(0, new Reward(0L));

    private final int matchCount;
    private final Reward reward;

    Rank(int matchCount, Reward reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        if (isMatchBonusAvailable(matchCount, matchBonus)) {
            return Rank.SECOND;
        }
        return findWithMatchCount(matchCount);
    }

    private static boolean isMatchBonusAvailable(int matchCount, boolean matchBonus) {
        return matchCount == SECOND.matchCount && matchBonus;
    }

    private static Rank findWithMatchCount(int matchCount) {
        return exceptMatchBonusAvailableRank().stream()
                .filter(rank -> rank.matchCount == matchCount)
                .findAny()
                .orElse(Rank.NONE);
    }

    private static List<Rank> exceptMatchBonusAvailableRank() {
        List<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ranks.remove(Rank.SECOND);
        return ranks;
    }

    public static Reward calculateReward(List<Rank> ranks) {
        Reward reward = new Reward(0L);
        for (Rank rank : ranks) {
            reward = reward.plus(rank.reward);
        }
        return reward;
    }

    public int findRewardCount(List<Rank> ranks) {
        return (int) ranks.stream().filter(rank -> rank == this).count();
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public Reward getReward() {
        return this.reward;
    }
}
