package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Money {

    private final BigInteger amount;
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public Money(BigInteger amount) {
        this.amount = amount;
    }

    public static Money valueOf(String amountValue) {
        validateNumeric(amountValue);
        return new Money(new BigInteger(amountValue));
    }

    public BigInteger toBigInteger() {
        return amount;
    }

    public static void validateNumeric(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
