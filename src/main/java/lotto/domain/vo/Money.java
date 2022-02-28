package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Objects;

public class Money {

    private static final int OVER_LIMIT_MONEY = 100_000;
    private static final int UNDER_LIMIT_MONEY = 1_000;
    private static final int MINIMUM_REWARD = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long value;

    private Money(long value) {
        this.value = value;
    }

    public static Money createMoneyByCount(int count) {
        return new Money(count * UNDER_LIMIT_MONEY);
    }

    public static Money createMoney(long value) {
        validateMoneyRange(value);
        return new Money(value);
    }

    private static void validateMoneyRange(long money) {
        validateOverLimit(money);
        validateUnderLimit(money);
    }

    private static void validateOverLimit(long money) {
        if (money > OVER_LIMIT_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0}을 넘을 수 없다.", OVER_LIMIT_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private static void validateUnderLimit(long money) {
        if (money < UNDER_LIMIT_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이상이어야 한다.", UNDER_LIMIT_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static Money createReward(long value) {
        validateRewardRange(value);
        return new Money(value);
    }

    private static void validateRewardRange(long value) {
        validatePositive(value);
    }

    private static void validatePositive(long value) {
        if (value < MINIMUM_REWARD) {
            throw new IllegalArgumentException("상금은 0이상이어야 한다.");
        }
    }

    public Money plus(Money money) {
        return new Money(this.value + money.value);
    }

    public Money minus(Money money) {
        return new Money(this.value - money.value);
    }

    public BigDecimal divide(Money money) {
        return BigDecimal.valueOf(this.value).divide(BigDecimal.valueOf(money.value), DECIMAL_PLACE, RoundingMode.DOWN);
    }

    public boolean isGreaterThan(Money money) {
        return this.value >= money.value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money1 = (Money) o;
        return value == money1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
