package domain;

import java.util.Arrays;

public enum LottoResult {

    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false);

    private static final int SECOND_AND_THIRD_MATCH_COUNT = 5;

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
                .filter(result -> filterOnFiveMatchCount(matchCount, hasBonus, result))
                .findFirst()
                .orElse(null);
    }

    private static boolean filterOnFiveMatchCount(int matchCount, boolean hasBonus, LottoResult result) {
        if (matchCount != SECOND_AND_THIRD_MATCH_COUNT) {
            return true;
        }
        return result.getHasBonus() == hasBonus;
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
