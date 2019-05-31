package lotto.domain;

public class Money {
    private final int money;
    private final static int MONEY_UNIT = 1000;

    public Money(int money) {
        this.money = money;
    }

    public int getRound() {
        return money / MONEY_UNIT;
    }
}
