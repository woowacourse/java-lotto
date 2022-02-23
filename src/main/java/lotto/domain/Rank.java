package lotto.domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000);

    private final int count;
    private final int money;

    Rank(int count, int money) {
        this.count = count;
        this.money = money;
    }
}
