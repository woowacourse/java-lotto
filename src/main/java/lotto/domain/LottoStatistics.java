package lotto.domain;

import java.util.Map;
import java.util.Set;

public class LottoStatistics {

    private final Map<LottoRank, Long> statisticsMap;

    public LottoStatistics(Map<LottoRank, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }


    public long getCount(LottoRank lottoRank) {
        return statisticsMap.computeIfAbsent(lottoRank, key -> 0L);
    }

    public double calculateYield(PurchasingPrice purchasingPrice) {
        Set<LottoRank> lottoRanks = statisticsMap.keySet();
        long priceTotal = 0;
        for (LottoRank lottoRank : lottoRanks) {
            priceTotal += lottoRank.getPrizeMoney() * statisticsMap.get(lottoRank);
        }
        int denominator = purchasingPrice.getPurchasingPrice();
        return (((double) priceTotal) / denominator);
    }
}
