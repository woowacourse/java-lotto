package lotto.domain.lotto;

import java.math.BigInteger;
import lotto.utils.StringValidator;

public class Money {

    private final BigInteger amount;

    public Money(BigInteger amount) {
        this.amount = amount;
    }

    public static Money valueOf(String amountValue) {
        //todo 도메인이 유틸 의존함
        StringValidator.validateIsDigit(amountValue);
        return new Money(new BigInteger(amountValue));
    }

    public BigInteger toBigInteger() {
        return amount;
    }
}
