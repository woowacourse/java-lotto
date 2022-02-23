package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private static final int INIT_COUNT = 0;
    private static final int ADD_NUMBER = 1;

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
        long total = 0L;
        for (Map.Entry<WinningPrice, Integer> entry : result.entrySet()) {
            total += (long)entry.getKey().getPrice() * entry.getValue();
        }
        double rateOfProfit = Math.round((double)total / money.getValue() * 1000) / 1000.0;

        return Double.parseDouble(String.format("%.3f", rateOfProfit));
    }
}
