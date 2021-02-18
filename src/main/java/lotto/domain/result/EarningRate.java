package lotto.domain.result;

import java.math.BigInteger;

public class EarningRate {

    private final BigInteger earningRate;

    public EarningRate(BigInteger totalPrize, BigInteger buyPrice) {
        this.earningRate = (totalPrize.multiply(BigInteger.valueOf(100)).divide(buyPrice));
    }

    public BigInteger toBigInteger() {
        return earningRate;
    }
}
