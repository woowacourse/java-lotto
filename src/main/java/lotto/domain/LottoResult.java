package lotto.domain;

import java.util.Map;

public class LottoResult {

    private static final String FORMAT = "%.2f";
    private final Map<Rank, Integer> rankResult;

    public LottoResult(Map<Rank, Integer> rankResult) {
        this.rankResult = rankResult;
    }

    public double findEarningRate(int money) {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : rankResult.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        return (double) sumOfPrize / (double) money;
    }
}
