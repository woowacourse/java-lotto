package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private static final long ZERO = 0;

    private final Map<LottoRank, Long> statistics;

    public LottoResult(Map<LottoRank, Long> statistics) {
        this.statistics = new EnumMap<>(statistics);
    }

    public long getTicketCountsByRank(LottoRank lottoRank) {
        return statistics.computeIfAbsent(lottoRank, key -> ZERO);
    }

    public double calculateYield(PurchasingPrice purchasingPrice) {
        long prizeMoneyTotal = calculatePrizeMoneyTotal();
        int denominatorCost = purchasingPrice.getPrice();
        return (((double) prizeMoneyTotal) / denominatorCost);
    }

    private long calculatePrizeMoneyTotal() {
        return statistics.keySet()
                .stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * statistics.get(lottoRank))
                .sum();
    }
}
