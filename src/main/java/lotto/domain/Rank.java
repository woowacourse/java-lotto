package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NOTHING(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private int countOfMatch;
    private int reward;

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

    public static Rank rankOf(int countOfMatch, boolean bonusNumber) {
        if (!bonusNumber && THIRD.matchCount(countOfMatch)) {
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
