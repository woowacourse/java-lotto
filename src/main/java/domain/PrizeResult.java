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
        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += (long) result.getOrDefault(rank, 0) * rank.getPrice();
        }
        return sum;
    }

    public double calculateProfit() {
        int money = lottoCount * LottoConstants.LOTTO_PRICE;
        return (double) calculatePrizeSum() / money;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(Rank.values())
                .filter(rank -> !isMiss(rank))
                .forEach(rank -> sb.append(rank.getMsg()).append(result.getOrDefault(rank, 0)).append("개\n"));
        return sb.toString();
    }

    private boolean isMiss(Rank rank) {
        if (rank == Rank.MISS) {
            return true;
        }

        return false;
    }
}
