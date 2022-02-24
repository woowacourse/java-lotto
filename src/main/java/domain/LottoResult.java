package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> resultCount;

    public LottoResult() {
        this.resultCount = new LinkedHashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.RANK_NOTHING) {
                continue;
            }
            resultCount.put(rank, 0);
        }
    }

    public long sumTotalPrice() {
        long totalPrice = 0;
        for (LottoRank rank : resultCount.keySet()) {
            totalPrice += (long) rank.getPrice() * resultCount.get(rank);
        }

        return totalPrice;
    }

    public Map<LottoRank, Integer> getResultCount() {
        return resultCount;
    }

    public void putLottoRank(LottoRank rank) {
        if (rank.equals(LottoRank.RANK_NOTHING)) {
            return;
        }
        resultCount.put(rank, resultCount.get(rank) + 1);
    }
}
