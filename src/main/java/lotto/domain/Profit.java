package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class Profit {
    private final Map<Rank, Integer> rankCounts;

    public Profit() {
        this.rankCounts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }
    }

    public void incrementCount(Rank key) {
        rankCounts.merge(key, 1, Integer::sum);
    }

    private long calculateTotalProfit() {

        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public String calculateAverageProfitRate(Money money) {
        final int amount = money.getAmount();
        long totalProfit = calculateTotalProfit();
        return new BigDecimal(totalProfit)
                .divide(new BigDecimal(amount), 2, RoundingMode.HALF_UP).toString();
    }

    public Map<Rank, Integer> getRankCounts() {
        return rankCounts;
    }
}
