package domain;

import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(0);
    private static final int ZERO_VALUE = 0;

    private final long value;

    private Money(long value) {
        validateMoney(value);
        this.value = value;
    }

    public static Money valueOf(long value) {
        return new Money(value);
    }

    public static Money createPurchasingLottoMoney(long value) {
        return new Money(value);
    }

    private void validateMoney(long value) {
        validateNegativeLottoMoney(value);
        validateMultipleLottoMoney(value);
    }

    private void validateNegativeLottoMoney(long value) {
        if (value < ZERO_VALUE) {
            throw new IllegalArgumentException("구입 금액은 0이상이어야 합니다.");
        }
    }

    private static void validateMultipleLottoMoney(long value) {
        if (value % Lotto.PRICE != ZERO_VALUE) {
            throw new IllegalArgumentException("구입 금액은 1,000원 단위만 입력 가능합니다.");
        }
    }

    public int divide(long value) {
        if (value <= ZERO_VALUE) {
            throw new IllegalArgumentException("0이하의 값으로 나눌 수 없습니다.");
        }

        return (int) (this.value / value);
    }

    public double calculateEarningRate(Money usedMoney) {
        double earningRate = this.value / (double) usedMoney.value;
        return (long) (earningRate * 100) / 100.0;
    }

    public Money multiply(long multiplier) {
        return new Money(value * multiplier);
    }

    public Money add(Money money) {
        return new Money(value + money.value);
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
