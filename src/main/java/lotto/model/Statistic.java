package lotto.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Statistic {

    private final Money inputMoney;
    private final Map<Rank, Integer> resultMap;

    public Statistic(Money inputMoney) {
        this.inputMoney = inputMoney;
        this.resultMap = new HashMap<>(
            Map.of(Rank.FIRST, 0, Rank.SECOND, 0,
                Rank.THIRD, 0, Rank.FOURTH, 0,
                Rank.FIFTH, 0, Rank.NOTHING, 0));
    }

    public int getCountByRank(Rank rank) {
        return resultMap.get(rank);
    }

    public void addRank(Rank rank) {
        resultMap.put(rank, getCountByRank(rank) + 1);
    }

    public BigDecimal getProfitRate() {
        return totalPrize().divide(inputMoney);
    }

    private Money totalPrize() {
        return resultMap.keySet().stream()
            .map(this::eachTotalPrize)
            .reduce(Money.ZERO, Money::plus);
    }

    private Money eachTotalPrize(Rank rank) {
        return rank.getPrize().multiply(getCountByRank(rank));
    }
}
