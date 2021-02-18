package lotto.domain;

import java.util.Map;

public class LottoStatistics {
    private static final long ZERO = 0;

    private final Map<LottoRank, Long> statisticsMap;

    public LottoStatistics(Map<LottoRank, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public long getTicketCountsByRank(LottoRank lottoRank) {
        return statisticsMap.computeIfAbsent(lottoRank, key -> ZERO);
    }

    public double calculateYield(PurchasingPrice purchasingPrice) {
        long prizeMoneyTotal = calculatePrizeMoneyTotal();
        int denominatorCost = purchasingPrice.getPrice();
        return (((double) prizeMoneyTotal) / denominatorCost);
    }

    private long calculatePrizeMoneyTotal() {
        return statisticsMap.keySet()
                .stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * statisticsMap.get(lottoRank))
                .sum();
    }
}
