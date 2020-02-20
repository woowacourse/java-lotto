package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoCalculator {
    public static double getTotalWinningPrice(HashMap<String, Integer> map) {
        double sum = 0;
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            sum += LottoType.valueOf(entry.getKey()).calculate(entry.getValue());
        }
        return sum;
    }

    public static long getProfit(HashMap<String, Integer> map, int money) {
        return Math.round((getTotalWinningPrice(map) / money) * 100);
    }
}
