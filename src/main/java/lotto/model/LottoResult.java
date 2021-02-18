package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LottoResult {

    private final Map<LottoRank, Integer> result;
    private int totalPurchaseAmount = 0;

    public LottoResult() {
        result = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
    }

    public void addRank(LottoRank lottoRank) {
        if (result.containsKey(lottoRank)) {
            int num = result.get(lottoRank);
            result.put(lottoRank, num + 1);
        }
        totalPurchaseAmount+=1000;
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public double getEarningsRate() {
        return (double) getTotalPrize() / (double) totalPurchaseAmount;
    }

    private int getTotalPrize() {
        int totalPrize = 0;
        for (Entry<LottoRank, Integer> entry : result.entrySet()) {
            totalPrize += (entry.getKey().getPrize() * entry.getValue());
        }
        return totalPrize;
    }
}
