package lottogame.domain;

import java.util.Arrays;

public enum Rank {
    NOT_FOUND(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private int count;
    private int money;

    Rank(int count, int money) {
        this.count = count;
        this.money = money;
    }

    public static Rank of(int count, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameAs(count))
                .filter(rank -> !rank.equals(THIRD) || !bonus)
                .findFirst()
                .orElse(NOT_FOUND);
    }

    public int getCount() {
        return count;
    }

    public int getMoney() {
        return money;
    }

    private boolean isSameAs(int count) {
        return this.count == count;
    }

    public boolean isNotFound() {
        return this.equals(NOT_FOUND);
    }

    public boolean isSecond() {
        return this.equals(SECOND);
    }

    public boolean isFound() {
        return !this.equals(NOT_FOUND);
    }
}
