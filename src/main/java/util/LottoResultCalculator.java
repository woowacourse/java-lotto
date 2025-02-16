package util;

import constant.WinningCount;
import java.util.Map;

public class LottoResultCalculator {
    public static Double calculateEarningRate(Map<WinningCount, Integer> result, int cost) {
        double sum = 0;
        for (WinningCount winningCount : result.keySet()) {
            sum += result.getOrDefault(winningCount, 0) * winningCount.getAmount();
        }
        return Math.round((sum * 100) / cost) / 100.0;
    }
}
