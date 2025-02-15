package model.result;

import static common.constant.NumberConstants.LOTTO_PRICE;

import java.util.EnumMap;

public class PrizeResult {
    private final EnumMap<Rank, Integer> result;
    private final int lottoCount;

    public PrizeResult(EnumMap<Rank, Integer> result, int lottoCount) {
        this.result = result;
        this.lottoCount = lottoCount;
    }

    private long calculatePrizeSum() {
        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += (long) result.getOrDefault(rank, 0) * rank.getPrice();
        }
        return sum;
    }

    public double calculateProfit() {
        int money = lottoCount * LOTTO_PRICE;
        return (double) calculatePrizeSum() / money;
    }

    public EnumMap<Rank, Integer> getResult() {
        return result;
    }
}
