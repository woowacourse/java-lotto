package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class Result {

    private static final int DEFAULT_VALUE = 0;
    private static final int ADD_NUMBER = 1;
    private static final long INIT_WINNING_PRICE = 0L;

    private final Map<Rank, Integer> result;

    public Result() {
        result = new EnumMap<>(Rank.class);
    }

    public void add(Rank key) {
        result.put(key, getCount(key) + ADD_NUMBER);
    }

    public int getCount(Rank key) {
        return result.getOrDefault(key, DEFAULT_VALUE);
    }

    public double getRateOfProfit(Money money) {
        long totalProfit = INIT_WINNING_PRICE;
        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            totalProfit += (long) entry.getKey().getPrice() * entry.getValue();
        }

        return money.calculateRateOfProfit(totalProfit);
    }
}
