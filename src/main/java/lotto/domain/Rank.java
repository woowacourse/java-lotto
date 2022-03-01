package lotto.domain;

import lotto.domain.vo.LottoMoney;
import lotto.domain.vo.Reward;

import java.util.function.BiPredicate;
import java.util.*;

public enum Rank {

    FIRST(6, new Reward(2_000_000_000L), (matchCount, matchBonus) -> matchCount == 6),
    SECOND(5, new Reward(30_000_000L), (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    THIRD(5, new Reward(1_500_000L), (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    FOURTH(4, new Reward(50_000L), (matchCount, matchBonus) -> matchCount == 4),
    FIFTH(3, new Reward(5_000L), (matchCount, matchBonus) -> matchCount == 3),
    NONE(0, new Reward(0L), (matchCount, matchBonus) -> matchCount <= 2);

    private final int matchCount;
    private final Reward reward;
    private final BiPredicate<Integer, Boolean> matchCalculator;

    Rank(int matchCount, Reward reward, BiPredicate<Integer, Boolean> matchCalculator) {
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
