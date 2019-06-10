package com.woowacourse.lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinningAggregator {
    private final Map<LottoResult, Integer> results;

    public WinningAggregator() {
        results = new HashMap<>();
    }

    public void addResult(LottoResult result) {
        results.putIfAbsent(result, 0);
        results.compute(result, (key, count) -> count + 1 );
    }

    public double calculateEarningRate(int unitPrice) {
        int totalLottoCount = results.keySet().stream()
            .mapToInt(results::get)
            .sum();
        long prizeMoneySum = results.keySet().stream()
            .mapToInt(k -> k.getPrizeMoney() * results.get(k))
            .sum();
        // 로또 구입 비용을 제외하고 얻은 수익금
        long earnedMoney = prizeMoneySum - totalLottoCount * unitPrice;
        return earnedMoney / (double) (totalLottoCount * unitPrice);
    }

    public int getResultCount(LottoResult result) {
        return results.getOrDefault(result, 0);
    }
}
