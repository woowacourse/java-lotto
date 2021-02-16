package lotto.money;

import java.util.Objects;

public class Money {
    private int money;

    public Money(String money) {
        this.money = validateNumber(money);
    }

    private int validateNumber(String money) {
        return Integer.parseInt(money);
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
