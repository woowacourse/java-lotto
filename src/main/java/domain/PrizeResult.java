package domain;

import constant.LottoConstants;
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
            sum += (long) result.getOrDefault(rank, 0) * rank.getPrize();
        }
        return sum;
    }

    public double calculateProfit() {
        int money = lottoCount * LottoConstants.LOTTO_PRICE;
        return (double) calculatePrizeSum() / money;
    }

    public int findByRank(Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}
