package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int PRICE = 1_000;
    private static final int PERCENT = 100;
    private final int value;

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    Money(double value) {
        this.value = (int) value;
    }

    int toLottosSize() {
        return this.value / PRICE;
    }

    private void validateMoney(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다");
        }
    }

    public int getValue() {
        return value;
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

    int toEarningRate(Money purchaseAmount) {
        return (int )(((double) value / purchaseAmount.value) * PERCENT);
    }
}
