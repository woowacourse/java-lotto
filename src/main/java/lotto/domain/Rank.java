package lotto.domain;

import java.util.Arrays;

public enum Rank {

    NOTHING(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000);

    private int countOfMatch;
    private int reward;
    private boolean matchBonus;

    Rank(int countOfMatch, int reward) {
        this(countOfMatch, reward, false);
    }

    Rank(int countOfMatch, int reward, boolean matchBonus) {
        this.countOfMatch = countOfMatch;
        this.reward = reward;
        this.matchBonus = matchBonus;
    }

    public int getCountOfMatch() {
        return this.countOfMatch;
    }

    public int getReward() {
        return reward;
    }

    public static Rank rankOf(int countOfMatch, boolean bonusNumber) {

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount(countOfMatch))
                .filter(rank -> !rank.equals(THIRD) || rank.matchBonus == bonusNumber)
                .findFirst()
                .orElse(NOTHING);
    }

    private boolean matchCount(int countOfMatch) {
        return getCountOfMatch() == countOfMatch;
    }
}
