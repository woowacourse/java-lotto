package lotto.domain;

import lotto.exception.MoneyException;

import java.util.Objects;

public class Money {
    private static final int MIN_PURCHASE_MONEY = 1_000;

    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money of(int winningMoney) {
        return new Money(winningMoney);
    }

    public static Money createPurchaseMoney(int moneyValue) {
        validateMoneyRange(moneyValue);
        return new Money(moneyValue);
    }

    private static void validateMoneyRange(int moneyValue) {
        if (isUnderMinPurchaseMoney(moneyValue)) {
            throw new MoneyException("구매금액은 1000원 이상이어야 합니다.");
        }
    }

    private static boolean isUnderMinPurchaseMoney(int moneyValue) {
        return moneyValue < MIN_PURCHASE_MONEY;
    }

    public Money plus(Money other) {
        return new Money(this.money + other.money);
    }

    public int calculateQuotient(Money other) {
        return this.money / other.money;
    }

    public int calculateProfit(Money purchaseMoney) {
        return (int) ((double) this.money / purchaseMoney.money * 100);
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
        return money + "원";
    }
}
