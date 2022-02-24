package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<LottoRank, Long> map = new EnumMap<>(LottoRank.class);

    public LottoStatistics(List<LottoRank> ranks) {
        for (LottoRank rank : LottoRank.values()) {
            long rankCount = findRankCount(ranks, rank);
            map.put(rank, rankCount);
        }
    }

    private long findRankCount(List<LottoRank> ranks, LottoRank rank) {
        return ranks.stream()
            .filter(win -> win != LottoRank.SIXTH && win == rank)
            .count();
    }

    public long count(LottoRank rank) {
        return map.get(rank);
    }

    public double calculateEarningRates(Money money) {
        long sum = map.entrySet().stream().mapToLong(x -> x.getKey().getPrize() * x.getValue())
            .sum();
        return (double) sum / money.getAmount();
    }

    public Map<LottoRank, Long> getMap() {
        return Collections.unmodifiableMap(map);
    }
}
