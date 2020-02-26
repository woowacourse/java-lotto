package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    BONUS(-1, 30000000),
    SECOND(5, 150000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    NONE(0, 0);

    private int matchCount;
    private int prizeAmount;

    Rank(int matchCount, int prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public static Rank find(int matchCount) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.matchCount == matchCount)
            .findFirst()
            .orElse(NONE);
    }

    public Rank getRightRank(boolean isContainingBonusNumber) {
        if (this.equals(Rank.SECOND) && isContainingBonusNumber) {
            return Rank.BONUS;
        }
        return this;
    }

    public int getPrize() {
        return prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

}
