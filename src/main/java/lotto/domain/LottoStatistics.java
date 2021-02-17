package lotto.domain;

import java.util.Map;

public class LottoStatistics {

    private final Map<LottoRank, Long> statisticsMap;

    public LottoStatistics(Map<LottoRank, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }


    public long getCount(LottoRank lottoRank) {
        return statisticsMap.computeIfAbsent(lottoRank, key -> 0L);
    }
}
