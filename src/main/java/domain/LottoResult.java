package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoRank, Integer> resultCount;

    public LottoResult() {
        this.resultCount = new LinkedHashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            initLottoRank(rank);
        }
    }

    public long sumTotalPrice() {
        long totalPrice = DEFAULT_VALUE;
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

    private void initLottoRank(LottoRank rank) { //TODO:
        if (rank == LottoRank.RANK_NOTHING) {
            return;
        }
        resultCount.put(rank, DEFAULT_VALUE);
    }
}
