package lotto.domain;

import java.util.*;
import java.util.function.Function;

public enum Rank {

    FIRST(6, new Reward(2_000_000_000L), (matchCount) -> matchCount == 6),
    SECOND(5, new Reward(30_000_000L), (matchCount) -> matchCount == 5),
    THIRD(5, new Reward(1_500_000L), (matchCount) -> matchCount == 5),
    FOURTH(4, new Reward(50_000L), (matchCount) -> matchCount == 4),
    FIFTH(3, new Reward(5_000L), (matchCount) -> matchCount == 3),
    NONE(0, new Reward(0L), (matchCount) -> matchCount <= 2),
    ERROR(7, new Reward(0L), (matchCount) -> matchCount >= 7);

    private final int matchCount;
    private final Reward reward;
    private Function<Integer, Boolean> matchCalculator;

    Rank(int matchCount, Reward reward, Function<Integer, Boolean> matchCalculator) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.matchCalculator = matchCalculator;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        if (isMatchBonusAvailable(matchCount, matchBonus)) {
            return matchBonusAvailableRank();
        }
        return findWithMatchCount(matchCount);
    }

    private static boolean isMatchBonusAvailable(int matchCount, boolean matchBonus) {
        return SECOND.matchCalculator.apply(matchCount) && matchBonus;
    }

    private static Rank findWithMatchCount(int matchCount) {
        return onlyMatchCountAvailableRanks().stream()
                .filter(rank -> rank.matchCalculator.apply(matchCount))
                .findAny()
                .orElseGet(() -> Rank.NONE);
    }

    private static List<Rank> onlyMatchCountAvailableRanks() {
        List<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ranks.remove(matchBonusAvailableRank());
        return ranks;
    }

    private static Rank matchBonusAvailableRank() {
        return Rank.SECOND;
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
