package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Objects;

public class Money {

    private static final int MINIMUM_LOTTO_MONEY = 1_000;
    private static final int MINIMUM_REWARD = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long value;

    private Money(long value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(long value) {
        if (value < MINIMUM_REWARD) {
            throw new IllegalArgumentException("돈은 0이상이어야 한다.");
        }
    }

    public static Money createMinimumLottoMoney() {
        return new Money(MINIMUM_LOTTO_MONEY);
    }

    public static Money createLottoMoneyByCount(int count) {
        return new Money(count * MINIMUM_LOTTO_MONEY);
    }

    public static Money createLottoMoney(long value) {
        validateLottoMoneyRange(value);
        return new Money(value);
    }

    private static void validateLottoMoneyRange(long value) {
        if (value < MINIMUM_LOTTO_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이상이어야 한다.", MINIMUM_LOTTO_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static Money createReward(long value) {
        return new Money(value);
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
