package lotto.domain;

import java.util.Arrays;
import java.util.List;

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
            return SECOND;
        }
        return findOtherRanks(plusMatchCountIsBonus(matchCount, matchBonus));
    }

    public static Money calculateReward(List<Rank> ranks) {
        Money reward = new Money(0L);
        for (Rank rank : ranks) {
            reward = reward.plus(rank.reward);
        }
        return reward;
    }

    private static boolean isSecond(int matchCount, boolean matchBonus) {
        return equalMatchCount(SECOND.matchCount, matchCount) && matchBonus;
    }

    private static int plusMatchCountIsBonus(int matchCount, boolean matchBonus) {
        if (matchBonus) {
            matchCount++;
        }
        return matchCount;
    }

    private static Rank findOtherRanks(int matchCount) {
        return Arrays.stream(Rank.values())
            .filter(rank -> equalMatchCount(matchCount, rank.matchCount))
            .filter(rank -> rank != SECOND)
            .findAny()
            .orElse(NONE);
    }

    private static boolean equalMatchCount(int matchCount, int otherMatchCount) {
        return matchCount == otherMatchCount;
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
