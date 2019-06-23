package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(5, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private int countOfMatch;

    private int reward;

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getReward() {
        return reward;
    }

    Rank(int countOfMatch, int reward) {
        this.countOfMatch = countOfMatch;
        this.reward = reward;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonusNumber) {
        if (countOfMatch == 5 && matchBonusNumber) {
            return SECOND;
        }

        if (countOfMatch == 5) {
            return THIRD;
        }

        return Stream.of(values())
                .filter(rank -> rank.getCountOfMatch() == countOfMatch)
                .findAny().orElse(MISS);
    }
}
