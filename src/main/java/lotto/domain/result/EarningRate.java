package lotto.domain.result;

import java.math.BigInteger;

public final class EarningRate {

    public static final BigInteger HUNDRED = BigInteger.valueOf(100);

    private final BigInteger earningRate;

    public EarningRate(BigInteger totalPrize, BigInteger paymentAmount) {
        this.earningRate = calculateEarningRate(totalPrize, paymentAmount);
    }

    private BigInteger calculateEarningRate(BigInteger totalPrize, BigInteger paymentAmount) {
        if (BigInteger.ZERO.equals(paymentAmount)) {
            return HUNDRED;
        }

        return totalPrize.multiply(HUNDRED).divide(paymentAmount);
    }

    public BigInteger toBigInteger() {
        return earningRate;
    }
}
