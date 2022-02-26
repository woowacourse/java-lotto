package lotto.domain;

import lotto.domain.vo.Money;

import java.util.*;

public enum Rank {

    FIRST(6, new Money(2_000_000_000L)),
    SECOND(5, new Money(30_000_000L)),
    THIRD(5, new Money(1_500_000L)),
    FOURTH(4, new Money(50_000L)),
    FIFTH(3, new Money(5_000L)),
    NONE(0, new Money(0L));

    private final int matchCount;
    private final Money reward;

    Rank(int matchCount, Money reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        if (isSecond(matchCount, matchBonus)) {
            return Rank.SECOND;
        }

        return getRank(matchCount);
    }

    private static boolean isSecond(int matchCount, boolean matchBonus) {
        return equalMatchCount(Rank.SECOND.matchCount, matchCount) && matchBonus;
    }

    private static Rank getRank(int matchCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> equalMatchCount(matchCount, rank.matchCount))
                .filter(Rank::isNotSecond)
                .findAny()
                .orElse(Rank.NONE);
    }

    private static boolean equalMatchCount(int matchCount, int otherMatchCount) {
        return matchCount == otherMatchCount;
    }

    private static boolean isNotSecond(Rank rank) {
        return rank != Rank.SECOND;
    }

    public static Money calculateReward(List<Rank> ranks) {
        Money reward = new Money(0L);
        for (Rank rank : ranks) {
            reward = reward.plus(rank.reward);
        }
        return reward;
    }

    public int findRewardCount(List<Rank> ranks) {
        int count = 0;
        for (Rank rank : ranks) {
            if (rank == this) {
                count++;
            }
        }
        return count;
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public Money getReward() {
        return this.reward;
    }
}
