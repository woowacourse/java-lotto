package domain;

import java.util.Arrays;

public enum LottoResult {

    FIFTH(3, 5000, false),
    FOURTH(4, 50000, false),
    THIRD(5, 1500000, false),
    SECOND(5, 30000000, true),
    FIRST(6, 2000000000, false);

    private final int matchCount;
    private final int prize;
    private final boolean hasBonus;

    LottoResult(int matchCount, int prize, boolean hasBonus) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.hasBonus = hasBonus;
    }

    public static LottoResult of(int matchCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(result -> result.hasSameMatchCount(matchCount))
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

    public boolean getHasBonus() {
        return this.hasBonus;
    }

    private boolean hasSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}
