package domain.money;

import java.util.Objects;

public class Money {
    private static final int MINIMUM_AMOUNT = 0;
    protected int amountOfMoney;

    protected Money(int amountOfMoney) {
        validateAmount(amountOfMoney);
        this.amountOfMoney = amountOfMoney;
    }

    private static void validateAmount(int amountOfMoney) {
        if (amountOfMoney < MINIMUM_AMOUNT) {
            throw new IllegalMoneyAmountException();
        }
    }

    public static Money amountOf(int amountOfMoney) {
        return new Money(amountOfMoney);
    }

    public int getAmount() {
        return amountOfMoney;
    }

    public Money getChangeOf(Money money) {
        return new Money(this.amountOfMoney - money.getAmount());
    }

    public Money multiply(int multiplier) {
        return new Money(amountOfMoney * multiplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amountOfMoney == money.amountOfMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfMoney);
    }
}
