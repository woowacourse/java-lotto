package domain;

import java.util.Objects;
import validator.MoneyValidator;

public class Money {
    private final int money;

    public Money(int money) {
        MoneyValidator.validate(money);
        this.money = money;
    }

    public int toLottoCount() {
        return money / 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}