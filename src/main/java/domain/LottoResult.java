package domain;

import java.util.Arrays;

public enum LottoResult {

    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private final int matchCount;
    private final int prize;

    LottoResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoResult of(int matchCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(result -> result.matchCount == matchCount)
                .filter(result -> !result.equals(THIRD) || !hasBonus)
                .findFirst()
                .orElse(null);
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public int getPrize() {
        return this.prize;
    }
}
