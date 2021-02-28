package lotto.domain;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> resultMap;
    private final BigInteger earningRate;

    public Result(Map<Rank, Integer> resultMap, BigInteger earningRate) {
        this.resultMap = resultMap;
        this.earningRate = earningRate;
    }

    public Map<Rank, Integer> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }

    public BigInteger getEarningRate() {
        return this.earningRate;
    }

}
