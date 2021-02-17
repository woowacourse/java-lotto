package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000),
    NOT_FOUNT(0, 0);

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
                .orElse(NOT_FOUNT);

    }

    private boolean isSameAs(int count) {
        return this.count == count;
    }

    public int getCount() {
        return count;
    }

    public int getMoney() {
        return money;
    }

    public boolean isNotFound() {
        return this.equals(NOT_FOUNT);
    }
}
