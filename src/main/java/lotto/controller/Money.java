package lotto.controller;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money plus(Money anotherMoney) {
        return new Money(this.money + anotherMoney.money);
    }

    public int calculateQuotient(Money anotherMoney) {
        return this.money / anotherMoney.money;
    }

    public int calculateProfit(Money purchaseMoney) {
        return (int)((double)this.money / purchaseMoney.money * 100);
    }

    public int getValue() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return money+"원";
    }
}
