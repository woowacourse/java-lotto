package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {

    Fifth(3, false, 5_000),
    Fourth(4, false, 50_000),
    Third(5, false, 1_500_000),
    Second(5, true, 30_000_000),
    First(6, false, 2_000_000_000);

    private final int count;
    private final boolean containBonus;
    private final int price;

    Rank(int count, boolean containBonus, int price) {
        this.count = count;
        this.containBonus = containBonus;
        this.price = price;
    }

    public static Optional<Rank> of(int count, boolean containBonus) {
        return Arrays.stream(values())
                .filter(it -> it.count == count)
                .filter(it -> it.containBonus == containBonus)
                .findAny();
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
