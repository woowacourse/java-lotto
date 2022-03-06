package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoStatistics {

    private static final int DECIMAL_POINT_CHANGE_NUMBER = 100;

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

    public String calculateEarningRates(Money money) {
        long sum = statisticsByRank.entrySet().stream()
            .mapToLong(singleLotto -> singleLotto.getKey().getPrize() * singleLotto.getValue())
            .sum();
        return convertFormat(money, sum);
    }

    private String convertFormat(Money money, long sum) {
        return String.format("%s", Math.floor(money.divideByAmount(sum)
            * DECIMAL_POINT_CHANGE_NUMBER) / DECIMAL_POINT_CHANGE_NUMBER);
    }

    public Map<LottoRank, Long> getStatisticsByRank() {
        return Collections.unmodifiableMap(statisticsByRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoStatistics that = (LottoStatistics) o;
        return Objects.equals(getStatisticsByRank(), that.getStatisticsByRank());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatisticsByRank());
    }
}
