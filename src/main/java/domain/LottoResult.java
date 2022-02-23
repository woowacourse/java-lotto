package domain;

import java.util.Arrays;

public enum LottoResult {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int matchCount;
    private final int prize;

    LottoResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoResult of(int matchCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(result -> result.hasSameMatchCount(matchCount))
                .filter(result -> !result.equals(SECOND) || hasBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getPrize() {
        return this.prize;
    }

    private boolean hasSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}
