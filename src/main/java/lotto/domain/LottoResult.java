package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private static final long ZERO = 0;

    private final Map<LottoRank, Long> statistics;

    public LottoResult(Map<LottoRank, Long> statistics) {
        initiateDefaultEntry(statistics);
        this.statistics = new EnumMap<>(statistics);
    }

    private void initiateDefaultEntry(Map<LottoRank, Long> statistics) {
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> statistics.computeIfAbsent(lottoRank, key -> ZERO));
    }

    public long getTicketCountsByRank(LottoRank lottoRank) {
        return statistics.computeIfAbsent(lottoRank, key -> ZERO);
    }

    public double calculateYield(int purchasingPrice) {
        return ((double) calculatePrizeMoneyTotal()) / purchasingPrice;
    }

    private long calculatePrizeMoneyTotal() {
        return statistics.keySet()
                .stream()
                .mapToLong(lottoRank -> lottoRank.getPrizeMoney() * statistics.get(lottoRank))
                .sum();
    }

    public Map<LottoRank, Long> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
