package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST_PRIZE(6, 2_000_000_000),
    SECOND_PRIZE(5, 30_000_000),
    THIRD_PRIZE(5, 1_500_000),
    FOURTH_PRIZE(4, 50_000),
    FIFTH_PRIZE(3, 5_000),
    NO_MATCH(0, 0);

    private final int matchCount;
    private final int winnings;

    Rank(int matchCount, int winnings) {
        this.matchCount = matchCount;
        this.winnings = winnings;
    }

    public static Rank of(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .filter(rank -> !rank.equals(SECOND_PRIZE) || hasBonusNumber)
                .findFirst()
                .orElse(NO_MATCH);
    }

    private boolean isSameMatchCount(int compareCount) {
        return matchCount == compareCount;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
