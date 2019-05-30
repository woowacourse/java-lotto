package lotto.domain;

import lotto.Exception.IllegalPriceException;

public class Money {
    private static final int MONEY_OFFSET = 1000;
    private final int money;

    public Money(int money) {
        if (money % MONEY_OFFSET != 0 || money < 0) {
            throw new IllegalPriceException();
        }

        this.money = money;
    }

    public int getSize(){
        return this.money / MONEY_OFFSET;
    }

}
