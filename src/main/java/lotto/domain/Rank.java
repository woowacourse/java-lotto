package lotto.domain;

import java.util.Arrays;

public enum Rank {

    MISS(0, 0, "3개 미만 일치"),
    FIFTH(3, 5000, "3개 일치"),
    FOURTH(4, 50000, "4개 일치"),
    THIRD(5, 1500000, "5개 일치"),
    SECOND(5, 30000000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, 2000000000, "6개 일치");

    private final int matchCount;
    private final int reward;
    private final String matchStatus;

    Rank(final int matchCount, final int reward, final String matchStatus) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.matchStatus = matchStatus;
    }

    public static Rank matchResult(int total) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == total)
                .findFirst()
                .orElse(Rank.valueOf(Rank.MISS.name()));
    }

    public double calculateTotalReward(Integer count) {
        return reward * count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public String getMatchStatus() {
        return matchStatus;
    }
}

