package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import static lotto.domain.Money.LOTTO_PRICE;

public class Statistics {
    private static final int DEFAULT_MONEY = 0;

    private Map<Lotto, Rank> ranks;

    public Statistics(Map<Lotto, Rank> ranks) {
        this.ranks = ranks;
    }

    public Map<Rank, Long> ranksStatistics() {
        Map<Rank, Long> rankResult = new LinkedHashMap<>();

        for (Rank value : Rank.values()) {
            rankResult.put(value, ranks.values()
                    .stream()
                    .filter(rank -> rank == value)
                    .count()
            );
        }

        return rankResult;
    }

    public double returnOfRate() {
        Map<Rank, Long> rankResult = ranksStatistics();
        double totalMoney = rankResult.values().stream().mapToInt(Long::intValue).sum() * LOTTO_PRICE;
        double returnMoney = DEFAULT_MONEY;

        for (Rank rank : Rank.values()) {
            returnMoney += rank.getReward() * rankResult.get(rank);
        }

        return returnMoney / totalMoney;
    }
}
