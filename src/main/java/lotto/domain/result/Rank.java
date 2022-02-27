package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NO_MATCH(0, 0);

    private int matchCount;
    private int money;

    Rank(final int matchCount, final int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank matchRank(final int matchCount, final boolean hasBonusNumber) {
        if (matchCount == 5 && hasBonusNumber) {
            return Rank.SECOND;
        }
        if (matchCount == 5 && !hasBonusNumber) {
            return Rank.THIRD;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .findFirst()
                .orElse(NO_MATCH);
    }

    private boolean isSameMatchCount(final int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }
}
