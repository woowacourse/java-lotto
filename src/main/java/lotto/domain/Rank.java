package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, 2_000_000_000L, (matchCount, matchBonus) -> matchCount == 6),
    SECOND(5, 30_000_000L, (matchCount, matchBonus) -> matchCount == 5 && matchBonus),
    THIRD(5, 1_500_000L, (matchCount, matchBonus) -> matchCount == 5 && !matchBonus),
    FOURTH(4, 50_000L, (matchCount, matchBonus) -> matchCount == 4),
    FIFTH(3, 5_000L, (matchCount, matchBonus) -> matchCount == 3),
    NONE(0, 0, (matchCount, matchBonus) -> matchCount < 3);

    private final int matchCount;
    private final long reward;
    private final BiPredicate<Integer, Boolean> match;

    Rank(int matchCount, long reward, BiPredicate<Integer, Boolean> match) {
        this.matchCount = matchCount;
        this.match = match;
        this.reward = reward;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.match.test(matchCount, matchBonus))
            .findAny()
            .orElse(NONE);
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
}
