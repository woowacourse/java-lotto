package model;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LottoCalculator {

    private final Map<LottoResult, Integer> winningLottoCounter;

    public LottoCalculator() {
        this.winningLottoCounter = Arrays.stream(LottoResult.values())
                .collect(
                        Collectors.toMap(
                                lottoResult -> lottoResult,
                                lottoResult -> 0)
                );
    }

    public void compareWinning(int count, boolean isBonus) {
        LottoResult targetResult = LottoResult.findTargetResult(count, isBonus);
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
