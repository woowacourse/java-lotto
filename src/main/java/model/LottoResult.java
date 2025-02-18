package model;

import java.util.Arrays;

public enum LottoResult {
    NOT_MATCH(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int targetCount;
    private final boolean isBonus;
    private final int price;

    LottoResult(int targetCount, boolean isBonus, int price) {
        this.targetCount = targetCount;
        this.isBonus = isBonus;
        this.price = price;
    }

    public static LottoResult findTargetResult(int winningNumberCount, boolean isBonus) {
        if (winningNumberCount == 5) {
            return findTargetResultUsingBonus(isBonus);
        }

        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.targetCount == winningNumberCount)
                .findAny()
                .orElse(NOT_MATCH);
    }

    private static LottoResult findTargetResultUsingBonus(boolean isBonus) {
        if (isBonus) {
            return LottoResult.SECOND;
        }
        return LottoResult.THIRD;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBonus() {
        return isBonus;
    }
}
