package lotto.domain.money;

import java.util.Objects;

import lotto.utils.MoneyUnit;

public class Money {

    private final int money;

    public Money(final int money) {
        MoneyValidator.validateMoney(money);
        this.money = money;
    }

    public int getQuotient() {
        return MoneyUnit.divide(money);
    }

    public double divide(final long money) {
        return (double) money / this.money;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Money otherMoney = (Money) object;
        return money == otherMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

}
