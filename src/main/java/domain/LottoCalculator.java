package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoCalculator {

    private static final int PERCENT = 100;
    private static final int INIT_SUM_VALUE = 0;

    private static double getTotalWinningPrice(HashMap<String, Integer> map) {
        double sum = INIT_SUM_VALUE;
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            sum += LottoType.valueOf(entry.getKey()).calculate(entry.getValue());
        }
        return sum;
    }

    public static long getProfit(HashMap<String, Integer> map, int money) {
        return Math.round((getTotalWinningPrice(map) / money) * PERCENT);
    }
}
