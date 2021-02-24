package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lotto.domain.lotto.Lotto;

public class LottoResult {

    private final Map<LottoRank, Integer> result;
    private int totalPurchaseAmount;

    public LottoResult() {
        result = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
        totalPurchaseAmount = 0;
    }

    public void addRank(LottoRank lottoRank) {
        result.computeIfPresent(lottoRank, (LottoRank key, Integer value) -> ++value);
        totalPurchaseAmount += Lotto.LOTTO_PRICE;
    }

    public void mergeResult(LottoResult lottoResult) {
        Map<LottoRank, Integer> mergeResult = lottoResult.getResult();
        for (LottoRank rank : mergeResult.keySet()) {
            int numOfMatch = mergeResult.get(rank);
            result.computeIfPresent(rank, (key, value) -> value += numOfMatch);
        }
    }

    public int findNumOfMatchByKey(LottoRank lottoRank) {
        return result.get(lottoRank);
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public double getEarningsRate() {
        return (double) getTotalPrize() / (double) totalPurchaseAmount;
    }

    public int getTotalPrize() {
        int totalPrize = 0;
        for (Entry<LottoRank, Integer> entry : result.entrySet()) {
            totalPrize += (entry.getKey().getPrize() * entry.getValue());
        }
        return totalPrize;
    }
}
