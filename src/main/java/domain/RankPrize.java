package domain;

import java.util.Arrays;

public enum RankPrize {

    FIRST(6, 2000000000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int matchedCount;
    private final int winPrice;

    RankPrize(final int matchedCount, final int winPrice) {
        this.matchedCount = matchedCount;
        this.winPrice = winPrice;
    }

    public static RankPrize of(final int matchedCount, final boolean isBonusMatched) {
        return Arrays.stream(RankPrize.values())
            .filter(winPrize -> winPrize.isSameWith(matchedCount))
            .filter(winPrize -> !isBonusMatched || winPrize.equals(SECOND))
            .findFirst()
            .orElse(NONE);
    }

    private boolean isSameWith(final int matchedCount) {
        return this.matchedCount == matchedCount;
    }

    public int getCount() {
        return matchedCount;
    }

    public int getPrice() {
        return winPrice;
    }
}
