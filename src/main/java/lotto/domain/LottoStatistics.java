package lotto.domain;

import java.util.Map;
import java.util.Set;

public class LottoStatistics {
    private static final long ZERO = 0;

    private final Map<LottoRank, Long> statisticsMap;

    public LottoStatistics(Map<LottoRank, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public long getCounts(LottoRank lottoRank) {
        return statisticsMap.computeIfAbsent(lottoRank, key -> ZERO);
    }

    public double calculateYield(Money money) {
        Set<LottoRank> lottoRanks = statisticsMap.keySet();
        long priceTotal = ZERO;
        for (LottoRank lottoRank : lottoRanks) {
            priceTotal += lottoRank.getPrizeMoney() * statisticsMap.get(lottoRank);
        }
        int denominator = money.getPurchasingPrice();
        return (((double) priceTotal) / denominator);
    }
}
