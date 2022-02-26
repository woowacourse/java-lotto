package lotto.domain;

import java.util.Arrays;

public enum WinningPrice {

    Fail(0, false, 0),
    Three(3, false, 5_000),
    Four(4, false, 50_000),
    Five(5, false, 1_500_000),
    FiveAndBonus(5, true, 30_000_000),
    All(6, false, 2_000_000_000);

    private final int count;
    private final boolean containBonus;
    private final int price;

    WinningPrice(int count, boolean containBonus, int price) {
        this.count = count;
        this.containBonus = containBonus;
        this.price = price;
    }

    public static WinningPrice of(int count, boolean containBonus) {
        return Arrays.stream(values())
                .filter(it -> it.count == count)
                .filter(it -> it.containBonus == containBonus)
                .findAny()
                .orElse(WinningPrice.Fail);
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
