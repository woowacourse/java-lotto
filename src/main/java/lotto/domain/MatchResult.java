package lotto.domain;

import java.util.Arrays;

public enum MatchResult {
    THREE_MATCH(3, 5_000),
    FOUR_MATCH(4, 50_000),
    FIVE_MATCH(5, 1_500_000),
    FIVE_MATCH_WITH_BONUS_BALL(5, 30_000_000),
    SIX_MATCH(6, 2_000_000_000);

    private int matchCount;
    private int prize;

    MatchResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    static MatchResult of(int matchCount, boolean containsBonusNumber) {
        if (matchCount != FIVE_MATCH.matchCount) {
            return Arrays.stream(values())
                    .filter(v -> matchCount == v.matchCount)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }

        if (containsBonusNumber) {
            return FIVE_MATCH_WITH_BONUS_BALL;
        }

        return FIVE_MATCH;
    }

    static boolean hasMatchCount(int matchCount) {
        return Arrays.stream(values())
                .anyMatch(v -> matchCount == v.matchCount);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
