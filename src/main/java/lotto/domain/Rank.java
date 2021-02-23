package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST_PRIZE(6, 2_000_000_000, "6개 일치"),
    SECOND_PRIZE(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD_PRIZE(5, 1_500_000, "5개 일치"),
    FOURTH_PRIZE(4, 50_000, "4개 일치"),
    FIFTH_PRIZE(3, 5_000, "3개 일치"),
    NO_MATCH(0, 0, "");

    private final int matchCount;
    private final int winnings;
    private final String message;

    Rank(int matchCount, int winnings, String message) {
        this.matchCount = matchCount;
        this.winnings = winnings;
        this.message = message;
    }

    public static Rank of(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .filter(rank -> !rank.equals(SECOND_PRIZE) || hasBonusNumber)
                .findFirst()
                .orElse(NO_MATCH);
    }

    private boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }

    public String getMessage() {
        return message;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
