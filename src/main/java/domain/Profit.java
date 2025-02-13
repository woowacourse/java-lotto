package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class Profit {
    Map<Rank, Integer> rankMap = new EnumMap<>(Rank.class);

    public void incrementCount(Rank key) {
        rankMap.merge(key, 1, Integer::sum);
    }

    private long calculateTotalProfit() {

        return rankMap.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public String calculateAverageProfitRate(Money money) {
        final int amount = money.getAmount();
        long totalProfit = calculateTotalProfit();
        return new BigDecimal(totalProfit)
                .divide(new BigDecimal(amount), 2, RoundingMode.HALF_UP).toString();
    }
}
