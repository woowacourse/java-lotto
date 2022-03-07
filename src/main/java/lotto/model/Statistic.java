package lotto.model;

import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class Statistic {

    private final Map<Rank, Integer> ranks;

    private Statistic(Collection<Rank> ranks) {
        this.ranks = Collections.unmodifiableMap(collectRanks(ranks));
    }

    private Map<Rank, Integer> collectRanks(Collection<Rank> ranks) {
        return collectRanksToMap(emptyRankMap(), ranks);
    }

    private Map<Rank, Integer> emptyRankMap() {
        return Stream.of(Rank.values())
            .collect(toMap(Function.identity(), r -> 0));
    }

    private Map<Rank, Integer> collectRanksToMap(Map<Rank, Integer> rankMap, Collection<Rank> ranks) {
        for (Rank rank : ranks) {
            rankMap.put(rank, rankMap.get(rank) + 1);
        }
        return rankMap;
    }

    public int getCountByRank(Rank rank) {
        return ranks.get(rank);
    }

    public ProfitRate getProfitRate() {
        return new ProfitRate(profitRate());
    }

    private BigDecimal profitRate() {
        if (lottoQuantity() == 0) {
            return ProfitRate.PROFIT_PRINCIPAL_RATE;
        }
        return totalPrize().divide(totalLottoPrice());
    }

    private int lottoQuantity() {
        return ranks.values().stream()
            .mapToInt(v -> v.intValue())
            .sum();
    }

    private Money totalLottoPrice() {
        return Lotto.PRICE.multiply(lottoQuantity());
    }

    private Money totalPrize() {
        return ranks.keySet().stream()
            .map(this::eachRankTotalPrize)
            .reduce(Money.ZERO, Money::plus);
    }

    private Money eachRankTotalPrize(Rank rank) {
        return rank.getPrize().multiply(getCountByRank(rank));
    }

    public static Statistic summarizeBy(Collection<Rank> ranks) {
        return new Statistic(ranks);
    }
}
