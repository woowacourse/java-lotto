package model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class LottoCalculator {

    private final Map<LottoResult, Integer> winningLottoCounter;

    public LottoCalculator() {
        this.winningLottoCounter = new EnumMap<>(LottoResult.class);
    }

    public void compareWinning(int winningNumberCount, boolean isBonus) {
        LottoResult targetResult = LottoResult.findTargetResult(winningNumberCount, isBonus);
        winningLottoCounter.put(targetResult, winningLottoCounter.getOrDefault(targetResult, 0) + 1);
    }

    public double lottoRateOfReturn(int price) {
        double result = (double) calculateWinnings() / price;
        result = Math.floor(result * 100);
        result /= 100;
        return result;
    }

    public int findTargetResultCount(LottoResult lottoResult) {
        return winningLottoCounter.getOrDefault(lottoResult, 0);
    }

    private int calculateWinnings() {
        int result = 0;
        for (Entry<LottoResult, Integer> lottoResult : winningLottoCounter.entrySet()) {
            result += lottoResult.getKey().getPrice() * lottoResult.getValue();
        }
        return result;
    }
}
