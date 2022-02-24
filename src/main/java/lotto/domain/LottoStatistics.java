package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<LottoRank, Long> statisticsByRank = new EnumMap<>(LottoRank.class);

    public LottoStatistics(List<LottoRank> ranks) {
        for (LottoRank rank : LottoRank.values()) {
            long rankCount = findRankCount(ranks, rank);
            statisticsByRank.put(rank, rankCount);
        }
    }

    private long findRankCount(List<LottoRank> ranks, LottoRank rank) {
        return ranks.stream()
            .filter(win -> win != LottoRank.SIXTH && win == rank)
            .count();
    }

    public long count(LottoRank rank) {
        return statisticsByRank.get(rank);
    }

    public double calculateEarningRates(Money money) {
        long sum = statisticsByRank.entrySet().stream()
            .mapToLong(singleLotto -> singleLotto.getKey().getPrize() * singleLotto.getValue())
            .sum();
        return (double) sum / money.getAmount();
    }

    public Map<LottoRank, Long> getStatisticsByRank() {
        return Collections.unmodifiableMap(statisticsByRank);
    }
}
