package lotto.domain;

import java.math.BigInteger;

public class EarningRate {

    private final BigInteger earningRate; // TODO 수익률 객체로 포장?

    public EarningRate(BigInteger totalPrize, BigInteger buyPrice) {
        this.earningRate = (totalPrize.multiply(BigInteger.valueOf(100)).divide(buyPrice));
    }

    public BigInteger toBigInteger() {
        return earningRate;
    }
}
