package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NOTHING(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int countOfMatch;
    private final int reward;

    Rank(int countOfMatch, int reward) {
        this.countOfMatch = countOfMatch;
        this.reward = reward;
    }

    public int getCountOfMatch() {
        return this.countOfMatch;
    }

    public int getReward() {
        return reward;
    }

    public static Rank rankOf(int countOfMatch, boolean hasBonusNumber) {
        if (!hasBonusNumber && THIRD.matchCount(countOfMatch)) {
            return THIRD;
        }
        return Arrays.stream(values())
                .filter(rank -> rank != THIRD)
                .filter(rank -> rank.matchCount(countOfMatch))
                .findFirst()
                .orElse(NOTHING);
    }

    private boolean matchCount(int countOfMatch) {
        return getCountOfMatch() == countOfMatch;
    }
}
