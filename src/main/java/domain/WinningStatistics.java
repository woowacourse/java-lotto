package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import constant.LottoConstant;

public class WinningStatistics {

    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoReward, Integer> statistics = new EnumMap<>(LottoReward.class);

    public WinningStatistics(List<LottoReward> lottoRewards) {
        Arrays.stream(LottoReward.values()).forEach(lottoReward -> statistics.put(lottoReward, DEFAULT_VALUE));
        lottoRewards.forEach(lottoReward -> statistics.replace(lottoReward, statistics.get(lottoReward) + 1));
    }

    public double calculateProfitRate() {
        int winningAmount = calculateWinningAmount();
        int purchasedLottoAmount = calculatePurchasedLottoAmount();

        return (double)winningAmount / purchasedLottoAmount;
    }

    private int calculateWinningAmount() {
        return statistics.entrySet().stream()
            .map(entry -> entry.getKey().getPrice() * entry.getValue())
            .reduce(DEFAULT_VALUE, Integer::sum);
    }

    private int calculatePurchasedLottoAmount() {
        int lottoCount = statistics.values().stream()
            .reduce(DEFAULT_VALUE, Integer::sum);

        return lottoCount * LottoConstant.LOTTO_PRICE;
    }

    public Map<LottoReward, Integer> getWinningStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
