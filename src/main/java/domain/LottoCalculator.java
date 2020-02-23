package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoCalculator {
    private static final int PERCENT = 100;
    private static final int INIT_SUM_VALUE = 0;

    private static double getTotalWinningPrice(Map<RankType, Integer> map) {
        double sum = INIT_SUM_VALUE;
        for (Map.Entry<RankType, Integer> entry : map.entrySet()) {
            sum += entry.getKey().calculate(entry.getValue());
        }
        return sum;
    }

    public static long getProfit(Map<RankType, Integer> map, int money) {
        return Math.round((getTotalWinningPrice(map) / money) * PERCENT);
    }
}
