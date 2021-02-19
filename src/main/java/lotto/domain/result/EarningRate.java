package lotto.domain.result;

import java.math.BigInteger;

public class EarningRate {

    private final BigInteger earningRate;

    public EarningRate(BigInteger totalPrize, BigInteger paymentAmount) {
        this.earningRate = (totalPrize.multiply(BigInteger.valueOf(100)).divide(paymentAmount));
    }

    public BigInteger toBigInteger() {
        return earningRate;
    }
}
