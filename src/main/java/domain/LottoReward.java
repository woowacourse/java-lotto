package domain;

import java.util.Arrays;

public enum LottoReward {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
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

    public static int prizeMoney(LottoReward reward, int lottoCount) {
        return reward.price * lottoCount;
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
