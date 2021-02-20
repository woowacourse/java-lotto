package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Objects;
import lotto.utils.NumericStringValidator;

public class Money {

    public static final String AMOUNT_SHOULD_BE_GREATER_THAN_ZERO = "구입금액은 0 이상의 정수여야합니다.";

    private final BigInteger amount;

    public Money(BigInteger amount) {
        if (lessThanZero(amount)) {
            throw new IllegalArgumentException(AMOUNT_SHOULD_BE_GREATER_THAN_ZERO);
        }
        this.amount = amount;
    }

    private boolean lessThanZero(BigInteger amount) {
        return amount.compareTo(BigInteger.ZERO) < 0;
    }

    public static Money valueOf(String amountValue) {
        validateNumeric(amountValue);
        return new Money(new BigInteger(amountValue));
    }

    private static void validateNumeric(String input) {
        if (!NumericStringValidator.isValid(input)) {
            throw new IllegalArgumentException(AMOUNT_SHOULD_BE_GREATER_THAN_ZERO);
        }
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
