package lotto.domain;

import java.util.Arrays;

public enum LottoRanking {

    Fail(0, false, 0),
    Three(3, false, 5_000),
    Four(4, false, 50_000),
    Five(5, false, 1_500_000),
    FiveAndBonus(5, true, 30_000_000),
    All(6, false, 2_000_000_000);

    private final int count;
    private final boolean containBonus;
    private final int price;

    LottoRanking(int count, boolean containBonus, int price) {
        this.count = count;
        this.containBonus = containBonus;
        this.price = price;
    }

    public static LottoRanking of(int count, boolean containBonus) {
        if (count == 5 && containBonus) {
            return LottoRanking.FiveAndBonus;
        }
        return Arrays.stream(values())
            .filter(it -> it.count == count)
            .findAny()
            .orElse(LottoRanking.Fail);
    }

    public long getPrice() {
        return price;
    }

    public long multiply(int count) {
        return (long) price * count;
    }

    public int getCount() {
        return count;
    }

    public boolean isContainBonus() {
        return containBonus;
    }
}
