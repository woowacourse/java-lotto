package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    BONUS(6, 30_000_000),
    SECOND(5, 150_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    private int matchCount;
    private int prizeAmount;

    Rank(int matchCount, int prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public static Rank find(int matchCount, boolean isBonus) {
        if (isBonus) {
            return BONUS;
        }
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.matchCount == matchCount)
            .findFirst()
            .orElse(NONE);
    }

    public int getPrize() {
        return prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
