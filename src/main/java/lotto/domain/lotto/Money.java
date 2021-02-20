package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.Objects;
import lotto.utils.NumericStringValidator;

public class Money {

    private final BigInteger amount;

    public Money(BigInteger amount) {
        this.amount = amount;
    }

    public static Money valueOf(String amountValue) {
        validateNumeric(amountValue);
        return new Money(new BigInteger(amountValue));
    }

    private static void validateNumeric(String input) {
        if (!NumericStringValidator.isValid(input)) {
            throw new NumberFormatException("구입금액은 0 이상의 정수여야합니다.");
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
