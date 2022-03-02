package domain;

import util.StringUtil;

public class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }
}
