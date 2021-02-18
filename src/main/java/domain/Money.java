package domain;

import java.util.Objects;

public class Money {
    private static final int ZERO = 0;

    private final long value;

    public Money(long value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(long value) {
        if (value < ZERO) {
            throw new IllegalArgumentException("구입 금액은 0이상이어야 합니다.");
        }
    }

    public int divide(long value) {
        if (value <= ZERO) {
            throw new IllegalArgumentException("0이하의 값으로 나눌 수 없습니다.");
        }

        return (int) (this.value / value);
    }

    public double calculateEarningRate(Money used) {
        double something = this.value / (double) used.value;
        return (long) (something * 100) / 100.0;
    }

    public Money multiply(long multiplier) {
        return new Money(value * multiplier);
    }

    public Money add(Money targetMoney) {
        return new Money(value + targetMoney.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public long getValue() {
        return value;
    }
}
