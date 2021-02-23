package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Objects;
import lotto.utils.NumericStringValidator;

public class Money {

    private final BigInteger amount;
    private static final int MINIMUM_AMOUNT = 0;

    public Money(BigInteger amount) {
        if (lessThanZero(amount)) {
            throw new IllegalArgumentException(String.format("구입금액은 %d 이상의 정수여야합니다.", MINIMUM_AMOUNT));
        }
        this.amount = amount;
    }

    private boolean lessThanZero(BigInteger amount) {
        return amount.compareTo(BigInteger.valueOf(MINIMUM_AMOUNT)) < 0;
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
