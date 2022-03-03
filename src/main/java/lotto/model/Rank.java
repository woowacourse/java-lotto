package lotto.model;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    FAIL(0, 0);

    private final int matchScore;
    private final long money;

    Rank(int matchScore, long money) {
        this.matchScore = matchScore;
        this.money = money;
    }

    static Rank find(int matchWinningNumbers, boolean isMatchBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.matchScore == matchWinningNumbers)
            .filter(isThirdOrSecond(isMatchBonus))
            .findFirst()
            .orElse(FAIL);
    }

    private static Predicate<Rank> isThirdOrSecond(boolean isMatchBonus) {
        return rank -> rank != THIRD || !isMatchBonus;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public long getMoney() {
        return money;
    }
}
