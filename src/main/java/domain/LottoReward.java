package domain;

import java.util.Arrays;

public enum LottoReward {

    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean hasBonus;
    private final int price;

    LottoReward(int matchCount, boolean hasBonus, int price) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.price = price;
    }

    public static LottoReward find(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
            .filter(reward -> reward.matchCount == matchCount && reward.hasBonus == hasBonus)
            .findFirst()
            .orElse(NONE);
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
