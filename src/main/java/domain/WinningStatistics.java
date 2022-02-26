package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final int DEFAULT_VALUE = 0;
    private static final int PLUS_COUNT = 1;

    private final Map<LottoReward, Integer> statisticsResult;

    public WinningStatistics(List<LottoReward> lottoRewards) {
        statisticsResult = new EnumMap<>(LottoReward.class);
        Arrays.stream(LottoReward.values()).forEach(lottoReward -> statisticsResult.put(lottoReward, DEFAULT_VALUE));

        for (LottoReward lottoReward : lottoRewards) {
            statisticsResult.replace(lottoReward, statisticsResult.get(lottoReward) + PLUS_COUNT);
        }
    }

    public double calculateProfitRate() {
        final int totalPrize = calculateTotalPrize();
        final int purchasedMoneyCount = calculatePurchasedMoneyCount();

        return totalPrize / ((double)purchasedMoneyCount * LottoMoney.LOTTO_PRICE);
    }

    private int calculateTotalPrize() {
        return statisticsResult.entrySet().stream()
            .map(result -> LottoReward.prizeMoney(result.getKey(), result.getValue()))
            .reduce(DEFAULT_VALUE, Integer::sum);
    }

    private int calculatePurchasedMoneyCount() {
        return statisticsResult.values().stream()
            .reduce(DEFAULT_VALUE, Integer::sum);
    }

    public Map<LottoReward, Integer> getWinningStatistics() {
        return Collections.unmodifiableMap(statisticsResult);
    }
}
