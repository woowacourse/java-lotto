package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<LottoRank, Integer> rankStatistic;

    public LottoResult(List<LottoRank> ranks) {
        rankStatistic = Collections.unmodifiableMap(
                ranks.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(), Collectors.summingInt(x -> 1)))
        );
    }

    public int getCountOf(LottoRank rank) {
        return rankStatistic.getOrDefault(rank, 0);
    }

    public BigDecimal getEarningRate() {
        return getRewards().divide(getExpense()).multiply(new BigDecimal(100));
    }

    private BigDecimal getExpense() {
        return rankStatistic.keySet().stream()
                .map(rank -> multiply(rankStatistic.get(rank), LottoTicket.PRICE))
                .reduce(new BigDecimal(0), BigDecimal::add)
                ;
    }

    public BigDecimal getRewards() {
        return rankStatistic.keySet().stream()
                .map(rank -> multiply(rank.getReward(), rankStatistic.get(rank)))
                .reduce(new BigDecimal(0), BigDecimal::add)
                ;
    }

    private BigDecimal multiply(int x, int y) {
        return new BigDecimal(x).multiply(new BigDecimal(y));
    }

    public List<RankCount> getRankCounts() {
        return Arrays.asList(LottoRank.values()).stream()
                .sorted(Comparator.comparingInt(LottoRank::getReward))
                .map(rank -> new RankCount(rank, getCountOf(rank)))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(rankStatistic, that.rankStatistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankStatistic);
    }
}
