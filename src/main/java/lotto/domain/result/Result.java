package lotto.domain.result;

import java.math.BigInteger;
import java.util.Map;
import lotto.domain.lotto.Rank;

public class Result {

    private final Map<Rank, Integer> resultMap;
    private final EarningRate earningRate;

    public Result(Map<Rank, Integer> resultMap, BigInteger paymentAmount) {
        this.resultMap = resultMap;
        this.earningRate = new EarningRate(getTotalPrize(resultMap), paymentAmount);
    }

    private BigInteger getTotalPrize(Map<Rank, Integer> resultMap) {
        BigInteger totalPrize = BigInteger.ZERO;

        for (Rank rank : resultMap.keySet()) {
            totalPrize = totalPrize.add(getPrizePerRank(rank));
        }

        return totalPrize;
    }

    private BigInteger getPrizePerRank(Rank rank) {
        return rank.getPrize().multiply(BigInteger.valueOf(resultMap.get(rank)));
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public BigInteger getEarningRate() {
        return earningRate.toBigInteger();
    }
}
