package domain;

import constant.LottoConstants;
import java.util.Arrays;
import java.util.EnumMap;

public class PrizeResult {

    private final EnumMap<Rank, Integer> result;
    private final int lottoCount;

    public PrizeResult(EnumMap<Rank, Integer> result, int lottoCount) {
        this.result = result;
        this.lottoCount = lottoCount;
    }

    private long calculatePrizeSum() {
        return Arrays.stream(Rank.values())
                .filter(rank -> !rank.isMiss())
                .mapToLong(rank -> (long) result.getOrDefault(rank, 0) * rank.getPrize())
                .sum();
    }

    public double calculateProfit() {
        int money = lottoCount * LottoConstants.LOTTO_PRICE;
        return (double) calculatePrizeSum() / money;
    }

    public int findByRank(Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}
