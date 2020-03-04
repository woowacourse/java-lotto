package lotto.model;

import java.util.Arrays;

public enum RankType {
    NONE(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 150_000, false),
    FIVE_BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private final int matchCount;
    private final int prize;
    private final boolean hasBonusBall;

    RankType(int matchCount, int prize, boolean hasBonusBall) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.hasBonusBall = hasBonusBall;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static RankType findLottoResult(int matchCount, boolean hasBonusBall) {
        return Arrays.stream(RankType.values())
            .filter(rankType -> rankType.matchCount == matchCount
                && rankType.hasBonusBall == hasBonusBall)
            .findFirst()
            .orElse(NONE);
    }
}
