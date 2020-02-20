package lotto.domain;

import java.util.Arrays;

public enum MatchResult {
    THREE_MATCH(3, 5_000),
    FOUR_MATCH(4, 50_000),
    FIVE_MATCH(5, 1_500_000),
    FIVE_MATCH_WITH_BONUSBALL(5, 30_000_000),
    SIX_MATCH(6, 2_000_000_000);

    private int matchCount;
    private int prize;

    MatchResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    static MatchResult of(int matchCount) {
        return Arrays.stream(values())
                .filter(v -> matchCount == v.matchCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 당첨 결과가 없습니다."));
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
