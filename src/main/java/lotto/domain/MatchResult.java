package lotto.domain;

import java.util.Arrays;

public enum MatchResult {
    THREE_MATCH(3, false, 5_000),
    FOUR_MATCH(4, false, 50_000),
    FIVE_MATCH(5, false, 1_500_000),
    FIVE_MATCH_WITH_BONUS_BALL(5, true, 30_000_000),
    SIX_MATCH(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private int matchCount;
    private boolean isBonus;
    private int prize;

    MatchResult(int matchCount, boolean isBonus, int prize) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.prize = prize;
    }

    static MatchResult of(int matchCount, boolean isBonus) {
        return Arrays.stream(values())
                .filter(v -> matchCount == v.matchCount && isBonus == v.isBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
