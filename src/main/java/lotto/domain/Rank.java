package lotto.domain;

import lotto.domain.vo.Money;

import java.util.function.BiPredicate;
import java.util.*;

public enum Rank {

    FIRST(6, Money.createReward(2_000_000_000L), (matchCount, matchBonus) -> matchCount == 6),
    SECOND(5, Money.createReward(30_000_000L), (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    THIRD(5, Money.createReward(1_500_000L), (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    FOURTH(4, Money.createReward(50_000L), (matchCount, matchBonus) -> matchCount == 4),
    FIFTH(3, Money.createReward(5_000L), (matchCount, matchBonus) -> matchCount == 3),
    NONE(0, Money.createReward(0L), (matchCount, matchBonus) -> matchCount <= 2);

    private final int matchCount;
    private final Money reward;
    private final BiPredicate<Integer, Boolean> matchCalculator;

    Rank(int matchCount, Money reward, BiPredicate<Integer, Boolean> matchCalculator) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.matchCalculator = matchCalculator;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCalculator.test(matchCount, matchBonus))
                .findAny()
                .orElse(Rank.NONE);
    }

    public static Money calculateReward(List<Rank> ranks) {
        Money reward = Money.createReward(0L);
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

    public Money getReward() {
        return this.reward;
    }
}
