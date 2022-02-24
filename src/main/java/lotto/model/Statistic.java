package lotto.model;

import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class Statistic {

    private final Money inputMoney;
    private final Map<Rank, Integer> rankMap;

    public Statistic(Money inputMoney) {
        this.inputMoney = inputMoney;
        this.rankMap = Stream.of(Rank.values())
            .collect(toMap(Function.identity(), r -> 0));
    }

    public int getCountByRank(Rank rank) {
        return rankMap.get(rank);
    }

    public void addRank(Rank rank) {
        rankMap.put(rank, getCountByRank(rank) + 1);
    }

    public BigDecimal getProfitRate() {
        return totalPrize().divide(inputMoney);
    }

    private Money totalPrize() {
        return rankMap.keySet().stream()
            .map(this::eachTotalPrize)
            .reduce(Money.ZERO, Money::plus);
    }

    private Money eachTotalPrize(Rank rank) {
        return rank.getPrize().multiply(getCountByRank(rank));
    }
}
