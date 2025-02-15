package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Profit {

    private static final int DEFAULT_INCREMENT = 1;
    private static final int SCALE_VALUE = 2;
    private static final int INIT_VALUE = 0;

    private final Map<Rank, Integer> rankCounts;

    public Profit() {
        this.rankCounts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, INIT_VALUE);
        }
    }

    public void incrementCount(Rank key) {
        rankCounts.merge(key, DEFAULT_INCREMENT, Integer::sum);
    }

    private long calculateTotalProfit() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public String calculateAverageProfitRate(Money money) {
        int amount = money.getAmount();
        long totalProfit = calculateTotalProfit();
        return new BigDecimal(totalProfit)
                .divide(new BigDecimal(amount), SCALE_VALUE, RoundingMode.HALF_UP).toString();
    }

    public List<Integer> getValues() {
        return rankCounts.values().stream().toList();
    }
}
