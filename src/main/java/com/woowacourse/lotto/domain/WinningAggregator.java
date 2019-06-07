package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.persistence.dto.AggregationDto;

import java.util.Arrays;
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

    public AggregationDto toDto() {
        AggregationDto dto = new AggregationDto();
        dto.setCntFirst(getResultCount(LottoResult.FIRST));
        dto.setCntSecond(getResultCount(LottoResult.SECOND));
        dto.setCntThird(getResultCount(LottoResult.THIRD));
        dto.setCntFourth(getResultCount(LottoResult.FOURTH));
        dto.setCntFifth(getResultCount(LottoResult.FIFTH));
        dto.setCntNone(getResultCount(LottoResult.NONE));
        dto.setPrizeMoneySum(Arrays.stream(LottoResult.values())
            .mapToLong(i -> getResultCount(i) * i.getPrizeMoney())
            .sum());
        return dto;
    }
}
