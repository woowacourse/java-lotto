package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final int DEFAULT_VALUE = 0;
    private static final int PLUS_COUNT = 1;
    public static final int SECOND_DECIMAL_MAKER_VALUE = 100;

    private final Map<LottoReward, Integer> statistics = new EnumMap<>(LottoReward.class);

    public WinningStatistics(List<LottoReward> lottoRewards) {
        Arrays.stream(LottoReward.values()).forEach(lottoReward -> statistics.put(lottoReward, DEFAULT_VALUE));

        for (LottoReward lottoReward : lottoRewards) {
            statistics.replace(lottoReward, statistics.get(lottoReward) + PLUS_COUNT);
        }
    }

    public double calculateProfitRate(int money) {
        double profit = 0;

        for (LottoReward lottoReward : statistics.keySet()) {
            int rewardCount = statistics.get(lottoReward);
            profit += rewardCount * lottoReward.getPrice();
        }

        return profit / money;
    }

    public Map<LottoReward, Integer> getWinningStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
