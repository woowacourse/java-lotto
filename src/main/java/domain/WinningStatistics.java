package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final int DEFAULT_VALUE = 0;
    private static final int PLUS_COUNT = 1;

    private final Map<LottoReward, Integer> statistics = new EnumMap<>(LottoReward.class);

    public WinningStatistics(List<LottoReward> lottoRewards) {
        for (LottoReward lottoReward : lottoRewards) {
            statistics.put(lottoReward, statistics.getOrDefault(lottoReward, DEFAULT_VALUE) + PLUS_COUNT);
        }
    }

    public Map<LottoReward, Integer> getWinningStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
