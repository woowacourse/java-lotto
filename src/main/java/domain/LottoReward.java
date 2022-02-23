package domain;

import java.util.Arrays;

public enum LottoReward {

    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int matchCount;
    private final int price;

    LottoReward(int matchCount, int price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public static LottoReward find(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
            .filter(reward -> reward.matchCount == matchCount)
            .filter(reward -> !reward.equals(SECOND) || hasBonus)
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
