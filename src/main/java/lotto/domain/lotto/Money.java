package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Objects;
import lotto.utils.NumericStringValidator;

public final class Money {

    private static final BigInteger MINIMUM_AMOUNT = BigInteger.valueOf(0);

    private final BigInteger amount;

    public Money(BigInteger amount) {
        validateGreaterThanMinimumAmount(amount);
        this.amount = amount;
    }

    private void validateGreaterThanMinimumAmount(BigInteger amount) {
        if (amount.compareTo(MINIMUM_AMOUNT) < 0) {
            throw new IllegalArgumentException(String.format("구입금액은 %d 이상의 정수여야합니다.", MINIMUM_AMOUNT));
        }
    }

    public static Money valueOf(String amountValue) {
        validateNumeric(amountValue);
        return new Money(new BigInteger(amountValue));
    }

    private static void validateNumeric(String input) {
        if (!NumericStringValidator.isValid(input)) {
            throw new IllegalArgumentException(String.format("구입금액은 %d 이상의 정수여야합니다.", MINIMUM_AMOUNT));
        }
    }

    public Money subtract(BigInteger value) {
        return new Money(this.amount.subtract(value));
    }

    public BigInteger toBigInteger() {
        return amount;
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
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
