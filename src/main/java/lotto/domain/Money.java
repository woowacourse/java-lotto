package lotto.domain;

import java.util.Objects;

public class Money {

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

    public Money minus(int value) {
        if (this.price < value) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }
        return Money.priceOf(this.price - value);
    }

    public boolean isLessThan(int price) {
        return this.price < price;
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
