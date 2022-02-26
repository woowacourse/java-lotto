package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private static final int INIT_COUNT = 0;
    private static final int ADD_NUMBER = 1;

    private final Map<LottoRanking, Integer> result;

    public Result() {
        result = new HashMap<>();
        for (LottoRanking value : LottoRanking.values()) {
            result.put(value, INIT_COUNT);
        }
    }

    public int getCount(LottoRanking key) {
        return result.get(key);
    }

    public void add(LottoRanking key) {
        result.put(key, result.get(key) + ADD_NUMBER);
    }

    public double getRateOfProfit(Money money) {
        long totalMoney = 0L;
        for (Map.Entry<LottoRanking, Integer> entry : result.entrySet()) {
            totalMoney += entry.getKey()
                .multiply(entry.getValue());
        }
        return money.getRateOfProfit(totalMoney);
    }
}
