package lotto.dto;

import java.util.Collections;
import java.util.Map;
import lotto.domain.LottoRank;

public class StatisticsResult {

    private final Map<LottoRank, Long> map;
    private final double earningRate;

    public StatisticsResult(Map<LottoRank, Long> map, double earningRate) {
        this.map = map;
        this.earningRate = earningRate;
    }

    public Map<LottoRank, Long> getMap() {
        return Collections.unmodifiableMap(map);
    }

    public double getEarningRate() {
        return earningRate;
    }
}
