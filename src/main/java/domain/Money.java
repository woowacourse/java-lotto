package domain;

import util.StringUtil;

public class Money {

    private int money;

    public Money(String money) {
        this.money = StringUtil.convertToInteger(money);
    }

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }
}
