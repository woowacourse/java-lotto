package lotto.domain;

import java.util.Objects;

public class Money {

    private static final Money ZERO = Money.priceOf(0);
    private final int price;

    private Money(int price) {
        this.price = price;
    }

    public static Money priceOf(int value) {
        validateMoney(value);
        return new Money(value);
    }

    private static void validateMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 마이너스가 될 수 없습니다.");
        }
    }

    public Money minus(int price) {
        return Money.priceOf(this.price - price);
    }

    public boolean isLessThan(int price) {
        if (this.price < price) {
            return true;
        }
        return false;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return price == money.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
