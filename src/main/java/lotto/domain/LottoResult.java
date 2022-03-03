package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result;

    public LottoResult(Map<LottoRank, Integer> rankMap) {
        this.result = new LinkedHashMap<>(rankMap);
    }

    public int sumOfPrize() {
        int sum = 0;
        for (LottoRank lottoRank : result.keySet()) {
            sum += result.get(lottoRank) * lottoRank.getPrizeAmount();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "LottoResult{" +
                "result=" + result +
                '}';
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public double calculateYield(Money money) {
        return sumOfPrize() / (double) money.getAmount();
    }
}
