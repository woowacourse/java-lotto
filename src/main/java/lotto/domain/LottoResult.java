package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> lottoResult;

    public LottoResult(Map<Rank, Integer> ranks) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        for (int i = 1; i < Rank.values().length; i++) {
            result.put(Rank.values()[i], 0);
        }
        for (Map.Entry<Rank, Integer> rank : ranks.entrySet()) {
            result.put(rank.getKey(), rank.getValue());
        }
        this.lottoResult = result;
    }

    public Map<Rank, Integer> getLottoResult() {
        return lottoResult;
    }

    public double findEarningRate(int money) {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : lottoResult.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        return (double) sumOfPrize / (double) money;
    }
}
