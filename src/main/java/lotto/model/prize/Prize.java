package lotto.model.prize;

import java.util.Arrays;

public enum Prize {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int matchCount;
    private final int amount;

    Prize(int matchCount, int amount) {
        this.matchCount = matchCount;
        this.amount = amount;
    }

    public static Prize getPrize(MatchResult matchResult) {
        if (!matchResult.isCount(SECOND.matchCount)) {
            return getPrizeByCount(matchResult);
        }
        if (matchResult.isBonus()) {
            return SECOND;
        }
        return THIRD;
    }

    private static Prize getPrizeByCount(MatchResult matchResult) {
        return Arrays.stream(values())
                .filter(prize -> matchResult.isCount(prize.matchCount))
                .findFirst()
                .orElse(NONE);
    }

    public int pickAmount(int count) {
        return this.amount * count;
    }
}
