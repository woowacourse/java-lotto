package lotto.domain;

import java.util.Arrays;

public enum LottoRankings {

    Fail(0, false, 0),
    Three(3, false, 5_000),
    Four(4, false, 50_000),
    Five(5, false, 1_500_000),
    FiveAndBonus(5, true, 30_000_000),
    All(6, false, 2_000_000_000);

    private final int count;
    private final boolean containBonus;
    private final int price;

    LottoRankings(int count, boolean containBonus, int price) {
        this.count = count;
        this.containBonus = containBonus;
        this.price = price;
    }

    public static LottoRankings of(int count, boolean containBonus) {
        if (count == 5 && containBonus) {
            return LottoRankings.FiveAndBonus;
        }
        if (count == 5 && !containBonus) {
            return LottoRankings.Five;
        }
        return Arrays.stream(values())
            .filter(it -> it.count == count)
            .findAny()
            .orElse(LottoRankings.Fail);
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public boolean isContainBonus() {
        return containBonus;
    }
}
