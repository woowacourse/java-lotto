package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result;

    public LottoResult(Map<LottoRank, Integer> rankMap) {
        this.result = new LinkedHashMap<>(rankMap);
    }

    private int sumOfPrize() {
        return result.keySet().stream()
                .mapToInt(lottoRank -> result.get(lottoRank) * lottoRank.getPrizeAmount())
                .sum();
    }

    public double calculateYield(Money money) {
        return sumOfPrize() / (double) money.getAmount();
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "LottoResult{" +
                "result=" + result +
                '}';
    }
}
