package domain;

import util.StringUtil;

public class Money {

    private final int money;

<<<<<<< HEAD
=======
    public Money(String money) {
        this.money = StringUtil.convertToInteger(money);
    }

>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }
}
