package lotto.model.result;

import java.util.Arrays;

public enum LottoRank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    SIXTH(0, 0, false);

    private static final int SPECIAL_CASE = 5;

    private final int prize;
    private final int matchCount;
    private final boolean isBonus;

    LottoRank(int prize, int matchCount, boolean isBonus) {
        this.prize = prize;
        this.matchCount = matchCount;
        this.isBonus = isBonus;
    }

    public static LottoRank find(int matchCount, boolean isBonusBall) {
        if (matchCount == SPECIAL_CASE) {
            return checkBonus(isBonusBall);
        }
        return Arrays.stream(values())
            .filter(value -> value.matchCount == matchCount)
            .findFirst()
            .orElse(SIXTH);
    }

    private static LottoRank checkBonus(boolean isBonusBall) {
        if (isBonusBall) {
            return LottoRank.SECOND;
        }
        return LottoRank.THIRD;
    }

    public int getPrize() {
        return this.prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
