package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private static final int INIT_COUNT = 0;
    private static final int ADD_NUMBER = 1;
    private static final long INIT_WINNING_PRICE = 0L;
    private static final double DECIMAL_PLACE = 1000.0;

    private final Map<WinningPrice, Integer> result;

    public Result() {
        result = new HashMap<>();
        for (WinningPrice value : WinningPrice.values()) {
            result.put(value, INIT_COUNT);
        }
    }

    public int getCount(WinningPrice key) {
        return result.get(key);
    }

    public void add(WinningPrice key) {
        result.put(key, result.get(key) + ADD_NUMBER);
    }

    public double getRateOfProfit(Money money) {
        long total = INIT_WINNING_PRICE;
        for (Map.Entry<WinningPrice, Integer> entry : result.entrySet()) {
            total += (long) entry.getKey().getPrice() * entry.getValue();
        }

        return Math.round((double) total / money.getValue() * DECIMAL_PLACE) / DECIMAL_PLACE;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}
