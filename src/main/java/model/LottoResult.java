package model;

import java.util.Arrays;

public enum LottoResult {
    NOT_MATCH(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private final int targetCount;
    private final boolean isBonus;
    private final int price;

    LottoResult(int targetCount, boolean isBonus, int price) {
        this.targetCount = targetCount;
        this.isBonus = isBonus;
        this.price = price;
    }

    public static LottoResult findTargetResult(int count, boolean isBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.targetCount == count && lottoResult.isBonus == isBonus)
                .findAny()
                .orElse(NOT_MATCH);
    }

    public int getPrice() {
        return price;
    }

    public boolean isBonus() {
        return isBonus;
    }
}
