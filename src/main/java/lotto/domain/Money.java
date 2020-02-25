package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int PRICE = 1_000;
    private static final int PERCENT = 100;
    private static final int ZERO = 0;
    private final int value;

    Money() {
        this.value = ZERO;
    }

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value <= ZERO) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다");
        }
    }

    int toLottosSize() {
        return this.value / PRICE;
    }

    Money add(Money money) {
        return new Money(this.value + money.value);
    }

    Money multiply(int size) {
        return new Money(this.value * size);
    }

    public int getValue() {
        return value;
    }

    int toEarningRate(Money purchaseAmount) {
        return (int)(((double)value / purchaseAmount.value) * PERCENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Money))
            return false;
        Money money = (Money)o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
