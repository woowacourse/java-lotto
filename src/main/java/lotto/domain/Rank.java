package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NOT_FOUNT(0, 0);

    private int count;
    private int money;

    Rank(int count, int money) {
        this.count = count;
        this.money = money;
    }

    public Rank of(int count, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameAs(count))
                .filter(rank -> !rank.equals(SECOND) || bonus)
                .findFirst()
                .orElse(NOT_FOUNT);
    }

    private boolean isSameAs(int count) {
        return this.count == count;
    }
}
